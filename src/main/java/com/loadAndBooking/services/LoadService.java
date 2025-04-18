package com.loadAndBooking.services;

import com.loadAndBooking.dto.LoadRequestDTO;
import com.loadAndBooking.dto.LoadResponseDTO;
import java.util.List;
import java.util.UUID;

public interface LoadService {
    LoadResponseDTO createLoad(LoadRequestDTO dto);
    LoadResponseDTO getLoadById(UUID id);
    List<LoadResponseDTO> getLoads(String shipperId, String truckType);
    LoadResponseDTO updateLoad(UUID id, LoadRequestDTO dto);
    void deleteLoad(UUID id);
}