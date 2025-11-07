package com.bitri.co.bw.Bitri_Projects_Dash.controller;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.HumanResource;
import com.bitri.co.bw.Bitri_Projects_Dash.model.HumanResourceRequest;
import com.bitri.co.bw.Bitri_Projects_Dash.model.HumanResourceResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.HumanResourceServiceIntf;

import java.util.List;

@RestController
@RequestMapping("/api/human-resources")
@RequiredArgsConstructor
@CrossOrigin("*")

public class HumanResouceController {

    private final HumanResourceServiceIntf humanResourceService;
    @PostMapping
    public ResponseEntity<HumanResourceResponse> create(@Valid @RequestBody HumanResourceRequest request) {
        HumanResourceResponse response = humanResourceService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<HumanResourceResponse> getHumanResourceById(@PathVariable Long id) {
        HumanResourceResponse response = humanResourceService.findById(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<HumanResourceResponse>> getAllHumanResourcesByProjectId(@PathVariable Long projectId) {
        List<HumanResourceResponse> resources = humanResourceService.findAllByProjectId(projectId);
        return ResponseEntity.ok(resources);
    }
    @PutMapping("/{id}")
    public ResponseEntity<HumanResourceResponse> update(@PathVariable Long id, @Valid @RequestBody HumanResourceRequest request) {
        HumanResourceResponse response = humanResourceService.update(id, request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        humanResourceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
