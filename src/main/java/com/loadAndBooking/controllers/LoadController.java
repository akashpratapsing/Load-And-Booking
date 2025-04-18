package com.loadAndBooking.controllers;

import com.loadAndBooking.dto.LoadRequestDTO;
import com.loadAndBooking.dto.LoadResponseDTO;
import com.loadAndBooking.services.LoadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/load")
public class LoadController {

    private final LoadService loadService;

    @Autowired
    public LoadController(LoadService loadService) {
        this.loadService = loadService;
    }

    @PostMapping
    public ResponseEntity<LoadResponseDTO> createLoad(@Valid @RequestBody LoadRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loadService.createLoad(dto));
    }

    @GetMapping
    public ResponseEntity<List<LoadResponseDTO>> getLoads(
            @RequestParam(required = false) String shipperId,
            @RequestParam(required = false) String truckType) {
        return ResponseEntity.ok(loadService.getLoads(shipperId, truckType));
    }

    @GetMapping("/{loadId}")
    public ResponseEntity<LoadResponseDTO> getLoadById(@PathVariable UUID loadId) {
        return ResponseEntity.ok(loadService.getLoadById(loadId));
    }

    @PutMapping("/{loadId}")
    public ResponseEntity<LoadResponseDTO> updateLoad(@PathVariable UUID loadId,
                                                      @Valid @RequestBody LoadRequestDTO dto) {
        return ResponseEntity.ok(loadService.updateLoad(loadId, dto));
    }

    @DeleteMapping("/{loadId}")
    public ResponseEntity<Void> deleteLoad(@PathVariable UUID loadId) {
        loadService.deleteLoad(loadId);
        return ResponseEntity.noContent().build();
    }
}
