package com.bitri.co.bw.Bitri_Projects_Dash.controller;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.HumanResource;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public List<HumanResource> getAll() {
        return humanResourceService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HumanResource> getById(@PathVariable Long id) {
        return humanResourceService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public HumanResource create(@RequestBody HumanResource humanResource) {
        return humanResourceService.save(humanResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HumanResource> update(@PathVariable Long id, @RequestBody HumanResource humanResource) {
        humanResource.setId(id);
        return ResponseEntity.ok(humanResourceService.save(humanResource));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        humanResourceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
