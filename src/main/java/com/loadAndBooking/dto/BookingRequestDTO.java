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

    public @NotNull(message = "Load ID is required") UUID getLoadId() {
        return loadId;
    }

    public void setLoadId(@NotNull(message = "Load ID is required") UUID loadId) {
        this.loadId = loadId;
    }

    public @NotBlank(message = "Transporter ID is required") String getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(@NotBlank(message = "Transporter ID is required") String transporterId) {
        this.transporterId = transporterId;
    }

    @DecimalMin(value = "0.0", inclusive = false, message = "Rate must be positive")
    public double getProposedRate() {
        return proposedRate;
    }

    public void setProposedRate(@DecimalMin(value = "0.0", inclusive = false, message = "Rate must be positive") double proposedRate) {
        this.proposedRate = proposedRate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}