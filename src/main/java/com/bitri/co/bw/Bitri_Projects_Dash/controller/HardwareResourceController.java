package com.bitri.co.bw.Bitri_Projects_Dash.controller;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.HardwareResource;
import com.bitri.co.bw.Bitri_Projects_Dash.model.HardwareResourceRequest;
import com.bitri.co.bw.Bitri_Projects_Dash.model.HardwareResourceResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.HardwareResourceServiceIntf;

import java.util.List;

@RestController
@RequestMapping("/api/hardware-resources")
@RequiredArgsConstructor
@CrossOrigin("*")

public class HardwareResourceController {

    private final HardwareResourceServiceIntf hardwareResourceService;
    @PostMapping
    public ResponseEntity<HardwareResourceResponse> create(@Valid @RequestBody HardwareResourceRequest request) {
        HardwareResourceResponse response = hardwareResourceService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HardwareResourceResponse> getHardwareResourceById(@PathVariable Long id) {
        HardwareResourceResponse response = hardwareResourceService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<HardwareResourceResponse>> getAllHardwareResourcesByProjectId(@PathVariable Long projectId) {
        List<HardwareResourceResponse> resources = hardwareResourceService.findAllByProjectId(projectId);
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HardwareResourceResponse> update(@PathVariable Long id, @Valid @RequestBody HardwareResourceRequest request) {
        HardwareResourceResponse response = hardwareResourceService.update(id, request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hardwareResourceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
