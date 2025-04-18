package com.loadAndBooking.services;

import com.loadAndBooking.dto.BookingRequestDTO;
import com.loadAndBooking.dto.BookingResponseDTO;
import java.util.List;
import java.util.UUID;

public interface BookingService {
    BookingResponseDTO createBooking(BookingRequestDTO dto);
    BookingResponseDTO getBookingById(UUID id);
    List<BookingResponseDTO> getBookings(String shipperId, String transporterId);
    BookingResponseDTO updateBooking(UUID id, BookingRequestDTO dto);
    void deleteBooking(UUID id);
}