package com.bitri.co.bw.Bitri_Projects_Dash.controller;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Project;
import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.ProjectStatus;
import com.bitri.co.bw.Bitri_Projects_Dash.model.DashboardMetricsDTO;
import com.bitri.co.bw.Bitri_Projects_Dash.model.ProjectStatusCountResponse;
import lombok.RequiredArgsConstructor;
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
    public List<Project> getAll() {
        return projectService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(@PathVariable Long id) {
        return projectService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project) {
        // make sure all children know their parent project
        if (project.getRisks() != null) {
            project.getRisks().forEach(r -> r.setProject(project));
        }
        if (project.getIssues() != null) {
            project.getIssues().forEach(i -> i.setProject(project));
        }
        if (project.getResources() != null) {
            project.getResources().forEach(res -> res.setProject(project));
        }
        if (project.getPerformance() != null) {
            project.getPerformance().setProject(project);
        }

        Project saved = projectService.save(project);
        return ResponseEntity.ok(saved);
    }




    @PutMapping("/{id}")
    public ResponseEntity<Project> update(@PathVariable Long id, @RequestBody Project project) {
        project.setId(id);

        if (project.getRisks() != null) {
            project.getRisks().forEach(r -> r.setProject(project));
        }
        if (project.getIssues() != null) {
            project.getIssues().forEach(i -> i.setProject(project));
        }
        if (project.getResources() != null) {
            project.getResources().forEach(res -> res.setProject(project));
        }
        if (project.getPerformance() != null) {
            project.getPerformance().setProject(project);
        }

        Project updated = projectService.save(project);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/counts/status")
    public ResponseEntity<List<ProjectStatusCountResponse>> getProjectStatusCounts() {
        // Replace mock data with real service call
        List<ProjectStatusCountResponse> counts = projectService.getProjectStatusCounts();
        return ResponseEntity.ok(counts);
    }


    @GetMapping("/metrics")
    public ResponseEntity<DashboardMetricsDTO> getDashboardMetrics() {
        DashboardMetricsDTO metrics = projectService.getDashboardMetrics();
        return ResponseEntity.ok(metrics);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
