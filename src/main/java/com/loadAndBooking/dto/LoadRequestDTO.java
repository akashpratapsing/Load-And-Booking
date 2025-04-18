package com.loadAndBooking.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoadRequestDTO {

    @NotBlank(message = "Shipper ID is required")
    private String shipperId;

    @NotBlank(message = "Loading point is required")
    private String loadingPoint;

    @NotBlank(message = "Unloading point is required")
    private String unloadingPoint;

    @NotNull(message = "Loading date is required")
    private LocalDateTime loadingDate;

    @NotNull(message = "Unloading date is required")
    private LocalDateTime unloadingDate;

    @NotBlank(message = "Product type is required")
    private String productType;

    @NotBlank(message = "Truck type is required")
    private String truckType;

    @Min(value = 1, message = "At least one truck is required")
    private int noOfTrucks;

    @DecimalMin(value = "0.1", message = "Weight must be greater than 0")
    private double weight;

    private String comment;

}