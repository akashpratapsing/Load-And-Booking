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

    public @NotBlank(message = "Shipper ID is required") String getShipperId() {
        return shipperId;
    }

    public void setShipperId(@NotBlank(message = "Shipper ID is required") String shipperId) {
        this.shipperId = shipperId;
    }

    public @NotBlank(message = "Loading point is required") String getLoadingPoint() {
        return loadingPoint;
    }

    public void setLoadingPoint(@NotBlank(message = "Loading point is required") String loadingPoint) {
        this.loadingPoint = loadingPoint;
    }

    public @NotBlank(message = "Unloading point is required") String getUnloadingPoint() {
        return unloadingPoint;
    }

    public void setUnloadingPoint(@NotBlank(message = "Unloading point is required") String unloadingPoint) {
        this.unloadingPoint = unloadingPoint;
    }

    public @NotNull(message = "Loading date is required") LocalDateTime getLoadingDate() {
        return loadingDate;
    }

    public void setLoadingDate(@NotNull(message = "Loading date is required") LocalDateTime loadingDate) {
        this.loadingDate = loadingDate;
    }

    public @NotNull(message = "Unloading date is required") LocalDateTime getUnloadingDate() {
        return unloadingDate;
    }

    public void setUnloadingDate(@NotNull(message = "Unloading date is required") LocalDateTime unloadingDate) {
        this.unloadingDate = unloadingDate;
    }

    public @NotBlank(message = "Product type is required") String getProductType() {
        return productType;
    }

    public void setProductType(@NotBlank(message = "Product type is required") String productType) {
        this.productType = productType;
    }

    public @NotBlank(message = "Truck type is required") String getTruckType() {
        return truckType;
    }

    public void setTruckType(@NotBlank(message = "Truck type is required") String truckType) {
        this.truckType = truckType;
    }

    @Min(value = 1, message = "At least one truck is required")
    public int getNoOfTrucks() {
        return noOfTrucks;
    }

    public void setNoOfTrucks(@Min(value = 1, message = "At least one truck is required") int noOfTrucks) {
        this.noOfTrucks = noOfTrucks;
    }

    @DecimalMin(value = "0.1", message = "Weight must be greater than 0")
    public double getWeight() {
        return weight;
    }

    public void setWeight(@DecimalMin(value = "0.1", message = "Weight must be greater than 0") double weight) {
        this.weight = weight;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}