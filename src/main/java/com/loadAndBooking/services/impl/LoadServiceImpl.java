package com.loadAndBooking.services.impl;

import com.loadAndBooking.dto.LoadRequestDTO;
import com.loadAndBooking.dto.LoadResponseDTO;
import com.loadAndBooking.entity.Load;
import com.loadAndBooking.enums.LoadStatus;
import com.loadAndBooking.repository.LoadRepository;
import com.loadAndBooking.services.LoadService;
import com.loadAndBooking.utils.LoadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LoadServiceImpl implements LoadService {

    @Autowired
    private LoadRepository loadRepository;

    @Override
    public LoadResponseDTO createLoad(LoadRequestDTO dto) {
        Load load = LoadMapper.toEntity(dto);
        load.setStatus(LoadStatus.POSTED);
        load.setDatePosted(LocalDateTime.now());
        Load savedLoad = loadRepository.save(load);
        return LoadMapper.toDTO(savedLoad);
    }

    @Override
    public LoadResponseDTO getLoadById(UUID id) {
        Load load = loadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Load not found"));
        return LoadMapper.toDTO(load);
    }

    @Override
    public List<LoadResponseDTO> getLoads(String shipperId, String truckType) {
        List<Load> loads;

        if (shipperId != null && truckType != null) {
            loads = loadRepository.findByShipperIdAndTruckType(shipperId, truckType);
        } else if (shipperId != null) {
            loads = loadRepository.findByShipperId(shipperId);
        } else if (truckType != null) {
            loads = loadRepository.findByTruckType(truckType);
        } else {
            loads = loadRepository.findAll();
        }

        return loads.stream()
                .map(LoadMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LoadResponseDTO updateLoad(UUID id, LoadRequestDTO dto) {
        Load load = loadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Load not found"));

        load.setShipperId(dto.getShipperId());

        if (load.getFacility() != null) {
            load.getFacility().setLoadingPoint(dto.getLoadingPoint());
            load.getFacility().setUnloadingPoint(dto.getUnloadingPoint());
            load.getFacility().setLoadingDate(dto.getLoadingDate());
            load.getFacility().setUnloadingDate(dto.getUnloadingDate());
        }

        load.setProductType(dto.getProductType());
        load.setTruckType(dto.getTruckType());
        load.setNoOfTrucks(dto.getNoOfTrucks());
        load.setWeight(dto.getWeight());
        load.setComment(dto.getComment());

        Load updatedLoad = loadRepository.save(load);
        return LoadMapper.toDTO(updatedLoad);
    }

    @Override
    public void deleteLoad(UUID id) {
        Load load = loadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Load not found"));
        loadRepository.delete(load);
    }
}
