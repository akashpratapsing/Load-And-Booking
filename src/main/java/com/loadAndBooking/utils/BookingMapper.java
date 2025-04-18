package com.loadAndBooking.utils;

import com.loadAndBooking.dto.BookingRequestDTO;
import com.loadAndBooking.dto.BookingResponseDTO;
import com.loadAndBooking.entity.Booking;
import com.loadAndBooking.entity.Load;
import com.loadAndBooking.enums.BookingStatus;

import java.time.LocalDateTime;

public class BookingMapper {

    public static Booking toEntity(BookingRequestDTO dto, Load load) {
        Booking booking = new Booking();
        booking.setLoad(load);
        booking.setTransporterId(dto.getTransporterId());
        booking.setProposedRate(dto.getProposedRate());
        booking.setComment(dto.getComment());
        booking.setStatus(BookingStatus.PENDING);  // Default status is PENDING
        booking.setRequestedAt(LocalDateTime.now());
        return booking;
    }

    public static BookingResponseDTO toResponseDTO(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setId(booking.getId());
        dto.setLoadId(booking.getLoad().getId());
        dto.setTransporterId(booking.getTransporterId());
        dto.setProposedRate(booking.getProposedRate());
        dto.setComment(booking.getComment());
        dto.setStatus(booking.getStatus());
        dto.setRequestedAt(booking.getRequestedAt());
        return dto;
    }
}