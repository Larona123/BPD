package com.bitri.co.bw.Bitri_Projects_Dash.controller;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Project;
import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.ProjectStatus;
import com.bitri.co.bw.Bitri_Projects_Dash.model.DashboardMetricsDTO;
import com.bitri.co.bw.Bitri_Projects_Dash.model.ProjectRequest;
import com.bitri.co.bw.Bitri_Projects_Dash.model.ProjectResponse;
import com.bitri.co.bw.Bitri_Projects_Dash.model.ProjectStatusCountResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.ProjectServiceIntf;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@CrossOrigin("*")

public class ProjectController {

    private final ProjectServiceIntf projectService;

    @GetMapping
    public List<ProjectResponse> getAll() {
        return projectService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getById(@PathVariable Long id) {
        return projectService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<ProjectResponse> create(@Valid @RequestBody ProjectRequest requestDTO) {
        ProjectResponse saved = projectService.save(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> update(
            @PathVariable Long id,
            @RequestBody ProjectRequest requestDTO) {

        ProjectResponse updated = projectService.update(id, requestDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/counts/status")
    public ResponseEntity<List<ProjectStatusCountResponse>> getProjectStatusCounts() {
        List<ProjectStatusCountResponse> counts = projectService.getProjectStatusCounts();
        return ResponseEntity.ok(counts);
    }

    @GetMapping("/metrics")
    public ResponseEntity<DashboardMetricsDTO> getDashboardMetrics() {
        DashboardMetricsDTO metrics = projectService.getDashboardMetrics();
        return ResponseEntity.ok(metrics);
    }
}
