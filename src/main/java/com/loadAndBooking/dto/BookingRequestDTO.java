package com.loadAndBooking.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.UUID;

@Data
public class BookingRequestDTO {

    @NotNull(message = "Load ID is required")
    private UUID loadId;

    @NotBlank(message = "Transporter ID is required")
    private String transporterId;

    @DecimalMin(value = "0.0", inclusive = false, message = "Rate must be positive")
    private double proposedRate;

    private String comment;

}