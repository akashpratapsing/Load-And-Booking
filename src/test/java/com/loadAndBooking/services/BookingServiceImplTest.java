package com.loadAndBooking.services;

import com.loadAndBooking.dto.BookingRequestDTO;
import com.loadAndBooking.dto.BookingResponseDTO;
import com.loadAndBooking.entity.Booking;
import com.loadAndBooking.entity.Load;
import com.loadAndBooking.enums.BookingStatus;
import com.loadAndBooking.enums.LoadStatus;
import com.loadAndBooking.repository.BookingRepository;
import com.loadAndBooking.repository.LoadRepository;
import com.loadAndBooking.services.impl.BookingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingServiceImplTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private LoadRepository loadRepository;

    @Test
    void testCreateBooking() {
        BookingRequestDTO request = new BookingRequestDTO();
        UUID loadId = UUID.randomUUID();
        request.setLoadId(loadId);
        request.setTransporterId("trans123");
        request.setProposedRate(2000.0);
        request.setComment("Fast delivery");

        Load load = new Load();
        load.setId(loadId);
        load.setStatus(LoadStatus.POSTED);

        when(loadRepository.findById(loadId)).thenReturn(Optional.of(load));
        when(loadRepository.save(any(Load.class))).thenReturn(load);

        Booking booking = new Booking();
        booking.setId(UUID.randomUUID());
        booking.setTransporterId("trans123");
        booking.setStatus(BookingStatus.PENDING);
        booking.setLoad(load);

        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        BookingResponseDTO response = bookingService.createBooking(request);

        assertEquals(BookingStatus.PENDING, response.getStatus());
        assertEquals("trans123", response.getTransporterId());
        verify(loadRepository).save(load);
        assertEquals(LoadStatus.BOOKED, load.getStatus());
    }

    @Test
    void testCreateBooking_whenLoadCancelled() {
        BookingRequestDTO request = new BookingRequestDTO();
        UUID loadId = UUID.randomUUID();
        request.setLoadId(loadId);

        Load load = new Load();
        load.setStatus(LoadStatus.CANCELLED);

        when(loadRepository.findById(loadId)).thenReturn(Optional.of(load));

        assertThrows(RuntimeException.class, () -> bookingService.createBooking(request));
    }

    @Test
    void testDeleteBooking_shouldCancelLoad() {
        UUID bookingId = UUID.randomUUID();
        Load load = new Load();
        load.setStatus(LoadStatus.BOOKED);

        Booking booking = new Booking();
        booking.setId(bookingId);
        booking.setLoad(load);

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));

        bookingService.deleteBooking(bookingId);

        verify(bookingRepository).delete(booking);
        assertEquals(LoadStatus.CANCELLED, load.getStatus());
        verify(loadRepository).save(load);
    }
}
