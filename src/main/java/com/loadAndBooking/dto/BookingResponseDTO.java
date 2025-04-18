package com.loadAndBooking.dto;

import com.loadAndBooking.enums.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BookingResponseDTO {
    private UUID id;
    private UUID loadId;
    private String transporterId;
    private double proposedRate;
    private String comment;
    private BookingStatus status;
    private LocalDateTime requestedAt;

}