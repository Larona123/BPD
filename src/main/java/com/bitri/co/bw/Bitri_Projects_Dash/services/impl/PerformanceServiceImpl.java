package com.bitri.co.bw.Bitri_Projects_Dash.services.impl;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Client;
import com.bitri.co.bw.Bitri_Projects_Dash.entity.Performance;
import com.bitri.co.bw.Bitri_Projects_Dash.entity.Project;
import com.bitri.co.bw.Bitri_Projects_Dash.model.ClientResponse;
import com.bitri.co.bw.Bitri_Projects_Dash.model.PerformanceRequest;
import com.bitri.co.bw.Bitri_Projects_Dash.model.PerformanceResponse;
import com.bitri.co.bw.Bitri_Projects_Dash.model.ProjectResponse;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.PerformanceRepository;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.PerformanceServiceIntf;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceServiceIntf {

    private final PerformanceRepository performanceRepository;
    private final ProjectRepository projectRepository;

    private ClientResponse mapClientToResponseDTO(Client client) {
        if (client == null) {
            return null;
        }
        return ClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .company(client.getCompany())
                .phoneNumber(client.getPhoneNumber())
                .build();
    }

    private ProjectResponse mapProjectToResponseDTO(Project project) {
        if (project == null) {
            return null;
        }

        ClientResponse ownerResponse = project.getOwner() != null ?
                mapClientToResponseDTO(project.getOwner()) :
                null;

        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .budget(project.getBudget())
                .description(project.getDescription())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .projectManager(project.getProjectManager())
                .status(project.getStatus())
                .owner(ownerResponse)
                .build();
    }

    private PerformanceResponse mapPerformanceToResponseDTO(Performance performance) {
        ProjectResponse projectResponse = performance.getProject() != null ?
                mapProjectToResponseDTO(performance.getProject()) :
                null;

        return PerformanceResponse.builder()
                .id(performance.getId())
                .plannedExpenditure(performance.getPlannedExpenditure())
                .actualExpenditure(performance.getActualExpenditure())
                .plannedSchedule(performance.getPlannedSchedule())
                .actualSchedule(performance.getActualSchedule())
                .project(projectResponse)
                .build();
    }
    @Override
    public List<PerformanceResponse> getAll() {
        return performanceRepository.findAll().stream()
                .map(this::mapPerformanceToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PerformanceResponse> getById(Long id) {
        return performanceRepository.findById(id)
                .map(this::mapPerformanceToResponseDTO);
    }

    @Override
    public PerformanceResponse save(PerformanceRequest performanceRequest) {
        Long projectId = performanceRequest.getProjectId();

        if (projectId == null) {
            throw new IllegalArgumentException("Project ID (projectId) is required to create a Performance record.");
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + projectId));

        Performance performance = new Performance();
        performance.setPlannedExpenditure(performanceRequest.getPlannedExpenditure());
        performance.setActualExpenditure(performanceRequest.getActualExpenditure());
        performance.setPlannedSchedule(performanceRequest.getPlannedSchedule());
        performance.setActualSchedule(performanceRequest.getActualSchedule());

        performance.setProject(project);

        Performance savedPerformance = performanceRepository.save(performance);
        return mapPerformanceToResponseDTO(savedPerformance);
    }

    @Override
    public Optional<PerformanceResponse> update(Long id, PerformanceRequest performanceRequest) {
        return performanceRepository.findById(id).map(existingPerformance -> {

            existingPerformance.setPlannedExpenditure(performanceRequest.getPlannedExpenditure());
            existingPerformance.setActualExpenditure(performanceRequest.getActualExpenditure());
            existingPerformance.setPlannedSchedule(performanceRequest.getPlannedSchedule());
            existingPerformance.setActualSchedule(performanceRequest.getActualSchedule());

            Long newProjectId = performanceRequest.getProjectId();

            if (newProjectId != null && (existingPerformance.getProject() == null || !newProjectId.equals(existingPerformance.getProject().getId()))) {
                Project newProject = projectRepository.findById(newProjectId)
                        .orElseThrow(() -> new RuntimeException("Cannot update: New Project ID not found."));
                existingPerformance.setProject(newProject);
            }

            Performance updatedPerformance = performanceRepository.save(existingPerformance);
            return mapPerformanceToResponseDTO(updatedPerformance);
        });
    }

    @Override
    public void delete(Long id) {
        performanceRepository.deleteById(id);
    }

}
