package com.loadAndBooking.entity;

import com.loadAndBooking.enums.LoadStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Load {

    @Id
    @GeneratedValue
    private UUID id;

    private String shipperId;

    @Embedded
    private Facility facility;

    private String productType;
    private String truckType;
    private int noOfTrucks;
    private double weight;
    private String comment;
    private LocalDateTime datePosted;

    @Enumerated(EnumType.STRING)
    private LoadStatus status = LoadStatus.POSTED;

}
