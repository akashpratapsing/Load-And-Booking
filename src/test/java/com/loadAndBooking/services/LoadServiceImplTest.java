package com.loadAndBooking.services;

import com.loadAndBooking.dto.LoadRequestDTO;
import com.loadAndBooking.dto.LoadResponseDTO;
import com.loadAndBooking.entity.Facility;
import com.loadAndBooking.entity.Load;
import com.loadAndBooking.enums.LoadStatus;
import com.loadAndBooking.repository.LoadRepository;
import com.loadAndBooking.services.impl.LoadServiceImpl;
import com.loadAndBooking.utils.LoadMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class LoadServiceImplTest {

    @InjectMocks
    private LoadServiceImpl loadService;

    @Mock
    private LoadRepository loadRepository;

    @Test
    void testCreateLoad() {
        LoadRequestDTO request = new LoadRequestDTO();
        request.setShipperId("shipper123");
        request.setProductType("Steel");
        request.setTruckType("Open");
        request.setNoOfTrucks(2);
        request.setWeight(10.5);
        request.setComment("Urgent");

        Load mockLoad = LoadMapper.toEntity(request);
        mockLoad.setStatus(LoadStatus.POSTED);
        mockLoad.setDatePosted(LocalDateTime.now());

        when(loadRepository.save(any(Load.class))).thenReturn(mockLoad);

        LoadResponseDTO response = loadService.createLoad(request);

        assertEquals(LoadStatus.POSTED, response.getStatus());
        assertEquals("shipper123", response.getShipperId());
    }

    @Test
    void testGetLoadById() {
        UUID id = UUID.randomUUID();
        Load mockLoad = new Load();
        mockLoad.setId(id);
        mockLoad.setShipperId("shipper");

        Facility facility = new Facility();
        facility.setLoadingPoint("Delhi");
        facility.setUnloadingPoint("Mumbai");

        mockLoad.setFacility(facility); // ✅ Attach facility to load

        Mockito.when(loadRepository.findById(id)).thenReturn(Optional.of(mockLoad));

        LoadResponseDTO response = loadService.getLoadById(id);

        assertEquals("shipper", response.getShipperId());
        assertEquals("Delhi", response.getLoadingPoint()); // optional
    }


    @Test
    void testDeleteLoad() {
        UUID id = UUID.randomUUID();
        Load mockLoad = new Load();
        mockLoad.setId(id);

        when(loadRepository.findById(id)).thenReturn(Optional.of(mockLoad));

        loadService.deleteLoad(id);

        verify(loadRepository, times(1)).delete(mockLoad);
    }
}
