package com.loadAndBooking.utils;

import com.loadAndBooking.dto.LoadRequestDTO;
import com.loadAndBooking.dto.LoadResponseDTO;
import com.loadAndBooking.entity.Facility;
import com.loadAndBooking.entity.Load;

public class LoadMapper {

    public static Load toEntity(LoadRequestDTO dto) {
        Load load = new Load();
        Facility facility = new Facility();
        facility.setLoadingPoint(dto.getLoadingPoint());
        facility.setUnloadingPoint(dto.getUnloadingPoint());
        facility.setLoadingDate(dto.getLoadingDate());
        facility.setUnloadingDate(dto.getUnloadingDate());

        load.setShipperId(dto.getShipperId());
        load.setFacility(facility);
        load.setProductType(dto.getProductType());
        load.setTruckType(dto.getTruckType());
        load.setNoOfTrucks(dto.getNoOfTrucks());
        load.setWeight(dto.getWeight());
        load.setComment(dto.getComment());
        load.setDatePosted(java.time.LocalDateTime.now());

        return load;
    }

    public static LoadResponseDTO toDTO(Load load) {
        LoadResponseDTO dto = new LoadResponseDTO();
        dto.setId(load.getId());
        dto.setShipperId(load.getShipperId());
        dto.setLoadingPoint(load.getFacility().getLoadingPoint());
        dto.setUnloadingPoint(load.getFacility().getUnloadingPoint());
        dto.setLoadingDate(load.getFacility().getLoadingDate());
        dto.setUnloadingDate(load.getFacility().getUnloadingDate());
        dto.setProductType(load.getProductType());
        dto.setTruckType(load.getTruckType());
        dto.setNoOfTrucks(load.getNoOfTrucks());
        dto.setWeight(load.getWeight());
        dto.setComment(load.getComment());
        dto.setDatePosted(load.getDatePosted());
        dto.setStatus(load.getStatus());
        return dto;
    }
}