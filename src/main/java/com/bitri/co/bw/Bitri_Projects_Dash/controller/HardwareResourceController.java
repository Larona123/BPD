package com.bitri.co.bw.Bitri_Projects_Dash.controller;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.HardwareResource;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public List<HardwareResource> getAll() {
        return hardwareResourceService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HardwareResource> getById(@PathVariable Long id) {
        return hardwareResourceService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public HardwareResource create(@RequestBody HardwareResource hardwareResource) {
        return hardwareResourceService.save(hardwareResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HardwareResource> update(@PathVariable Long id, @RequestBody HardwareResource hardwareResource) {
        hardwareResource.setId(id);
        return ResponseEntity.ok(hardwareResourceService.save(hardwareResource));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hardwareResourceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
