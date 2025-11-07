package com.bitri.co.bw.Bitri_Projects_Dash.services.impl;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.*;
import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.ProjectStatus;
import com.bitri.co.bw.Bitri_Projects_Dash.model.*;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.HumanResourceRepository;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.HumanResourceServiceIntf;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HumanResourceServiceImpl implements HumanResourceServiceIntf {

    private final HumanResourceRepository humanResourceRepository;
    private final ProjectRepository projectRepository;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private ClientResponse mapToOwnerResponse(Client owner) {
        if (owner == null) return null;
        return ClientResponse.builder()
                .id(owner.getId())
                .name(owner.getName())
                .email(owner.getEmail())
                .company(owner.getCompany())
                .phoneNumber(owner.getPhoneNumber())
                .build();
    }

    private ProjectResponse mapToProjectResponse(Project project) {
        if (project == null) return null;
        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .budget(project.getBudget())
                .description(project.getDescription())
                .startDate(LocalDate.parse(project.getStartDate().format(DATE_FORMATTER)))
                .endDate(LocalDate.parse(project.getEndDate().format(DATE_FORMATTER)))
                .projectManager(project.getProjectManager())
                .status(ProjectStatus.valueOf(project.getStatus().toString()))
                .owner(mapToOwnerResponse(project.getOwner()))
                .build();
    }

    private HumanResourceResponse mapToHumanResourceResponse(HumanResource resource) {
        return HumanResourceResponse.builder()
                .id(resource.getId())
                .name(resource.getName())
                .cost(resource.getCost())
                .type(resource.getType().toString())
                .projectId(resource.getProject().getId())
                .project(mapToProjectResponse(resource.getProject()))
                .role(resource.getRole())
                .hourlyRate(resource.getHourlyRate())
                .hoursAllocated(resource.getHoursAllocated())
                .startDate(resource.getStartDate())
                .endDate(resource.getEndDate())
                .build();
    }

    @Override
    public HumanResourceResponse create(HumanResourceRequest request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + request.getProjectId()));

        HumanResource resource = HumanResource.builder()
                .name(request.getName())
                .cost(request.getCost())
                .type(String.valueOf(Resource.ResourceType.HUMAN))
                .role(request.getRole())
                .hourlyRate(request.getHourlyRate())
                .hoursAllocated(request.getHoursAllocated())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .project(project)
                .build();

        HumanResource savedResource = humanResourceRepository.save(resource);
        return mapToHumanResourceResponse(savedResource);
    }

    @Override
    public List<HumanResourceResponse> findAll() {
        return humanResourceRepository.findAll().stream()
                .map(this::mapToHumanResourceResponse)
                .collect(Collectors.toList());
    }

    @Override
    public HumanResourceResponse findById(Long id) {
        HumanResource resource = humanResourceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Human Resource not found with ID: " + id));
        return mapToHumanResourceResponse(resource);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HumanResourceResponse> findAllByProjectId(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new EntityNotFoundException("Project not found with ID: " + projectId);
        }

        List<HumanResource> resources = humanResourceRepository.findAllByProjectId(projectId);

        return resources.stream()
                .map(this::mapToHumanResourceResponse)
                .collect(Collectors.toList());
    }

    @Override
    public HumanResourceResponse update(Long id, HumanResourceRequest request) {
        HumanResource existing = humanResourceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Human Resource not found with ID: " + id));

        Project newProject = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + request.getProjectId()));

        existing.setName(request.getName());
        existing.setCost(request.getCost());
        existing.setRole(request.getRole());
        existing.setHourlyRate(request.getHourlyRate());
        existing.setHoursAllocated(request.getHoursAllocated());
        existing.setStartDate(request.getStartDate());
        existing.setEndDate(request.getEndDate());
        existing.setProject(newProject);

        HumanResource updatedResource = humanResourceRepository.save(existing);
        return mapToHumanResourceResponse(updatedResource);
    }

    @Override
    public void delete(Long id) {
        humanResourceRepository.deleteById(id);
    }
}
