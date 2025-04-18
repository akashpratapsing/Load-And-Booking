package com.loadAndBooking.services.impl;

import com.loadAndBooking.dto.BookingRequestDTO;
import com.loadAndBooking.dto.BookingResponseDTO;
import com.loadAndBooking.entity.Booking;
import com.loadAndBooking.entity.Load;
import com.loadAndBooking.enums.BookingStatus;
import com.loadAndBooking.enums.LoadStatus;
import com.loadAndBooking.repository.BookingRepository;
import com.loadAndBooking.repository.LoadRepository;
import com.loadAndBooking.services.BookingService;
import com.loadAndBooking.utils.BookingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private LoadRepository loadRepository;

    @Override
    public BookingResponseDTO createBooking(BookingRequestDTO dto) {
        Load load = loadRepository.findById(dto.getLoadId())
                .orElseThrow(() -> new RuntimeException("Load not found"));

        if (load.getStatus() == LoadStatus.CANCELLED) {
            throw new RuntimeException("Cannot create booking, load is cancelled");
        }

        load.setStatus(LoadStatus.BOOKED);
        loadRepository.save(load);

        Booking booking = BookingMapper.toEntity(dto, load);
        booking.setStatus(BookingStatus.PENDING);
        booking.setRequestedAt(LocalDateTime.now());

        Booking savedBooking = bookingRepository.save(booking);
        return BookingMapper.toResponseDTO(savedBooking);
    }

    @Override
    public BookingResponseDTO getBookingById(UUID id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return BookingMapper.toResponseDTO(booking);
    }

    @Override
    public List<BookingResponseDTO> getBookings(String shipperId, String transporterId) {
        List<Booking> bookings;

        if (shipperId != null && transporterId != null) {
            bookings = bookingRepository.findByLoad_ShipperIdAndTransporterId(shipperId, transporterId);
        } else if (shipperId != null) {
            bookings = bookingRepository.findByLoad_ShipperId(shipperId);
        } else if (transporterId != null) {
            bookings = bookingRepository.findByTransporterId(transporterId);
        } else {
            bookings = bookingRepository.findAll();
        }

        return bookings.stream()
                .map(BookingMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponseDTO updateBooking(UUID id, BookingRequestDTO dto) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setTransporterId(dto.getTransporterId());
        booking.setProposedRate(dto.getProposedRate());
        booking.setComment(dto.getComment());

        if (booking.getStatus() == BookingStatus.PENDING) {
            booking.setStatus(BookingStatus.ACCEPTED);
        }

        Booking updatedBooking = bookingRepository.save(booking);
        return BookingMapper.toResponseDTO(updatedBooking);
    }

    @Override
    public void deleteBooking(UUID id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Load load = booking.getLoad();
        bookingRepository.delete(booking);

        if (load != null) {
            load.setStatus(LoadStatus.CANCELLED);
            loadRepository.save(load);
        }
    }
}
