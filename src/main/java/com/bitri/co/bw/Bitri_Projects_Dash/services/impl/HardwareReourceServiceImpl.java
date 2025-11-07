package com.bitri.co.bw.Bitri_Projects_Dash.services.impl;


import com.bitri.co.bw.Bitri_Projects_Dash.entity.Client;
import com.bitri.co.bw.Bitri_Projects_Dash.entity.HardwareResource;
import com.bitri.co.bw.Bitri_Projects_Dash.entity.Project;
import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.ProjectStatus;
import com.bitri.co.bw.Bitri_Projects_Dash.model.ClientResponse;
import com.bitri.co.bw.Bitri_Projects_Dash.model.HardwareResourceRequest;
import com.bitri.co.bw.Bitri_Projects_Dash.model.HardwareResourceResponse;
import com.bitri.co.bw.Bitri_Projects_Dash.model.ProjectResponse;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.bitri.co.bw.Bitri_Projects_Dash.entity.Resource;
import org.springframework.stereotype.Service;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.HardwareResourceRepository;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.HardwareResourceServiceIntf;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HardwareReourceServiceImpl implements HardwareResourceServiceIntf {
    private final HardwareResourceRepository hardwareResourceRepository;
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

    private HardwareResourceResponse mapToHardwareResourceResponse(HardwareResource resource) {
        return HardwareResourceResponse.builder()
                .id(resource.getId())
                .name(resource.getName())
                .cost(resource.getCost())
                .type(resource.getType())
                .serialNumber(resource.getSerialNumber())
                .underWarranty(resource.getUnderWarranty())
                .allocationDate(resource.getAllocationDate())
                .returnDate(resource.getReturnDate())
                .projectId(resource.getProject().getId())
                .project(mapToProjectResponse(resource.getProject()))
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<HardwareResourceResponse> findAllByProjectId(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new EntityNotFoundException("Project not found with ID: " + projectId);
        }

        List<HardwareResource> resources = hardwareResourceRepository.findAllByProjectId(projectId);

        return resources.stream()
                .map(this::mapToHardwareResourceResponse)
                .collect(Collectors.toList());
    }
    @Override
    public HardwareResourceResponse create(HardwareResourceRequest request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + request.getProjectId()));

        HardwareResource resource = HardwareResource.builder()
                .name(request.getName())
                .cost(request.getCost())
                .type(String.valueOf(Resource.ResourceType.HARDWARE))
                .serialNumber(request.getSerialNumber())
                .underWarranty(request.getUnderWarranty())
                .allocationDate(request.getAllocationDate())
                .returnDate(request.getReturnDate())
                .project(project)
                .build();

        HardwareResource savedResource = hardwareResourceRepository.save(resource);
        return mapToHardwareResourceResponse(savedResource);
    }

    @Override
    public List<HardwareResourceResponse> findAll() {
        return hardwareResourceRepository.findAll().stream()
                .map(this::mapToHardwareResourceResponse)
                .collect(Collectors.toList());
    }

    @Override
    public HardwareResourceResponse findById(Long id) {
        HardwareResource resource = hardwareResourceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hardware Resource not found with ID: " + id));
        return mapToHardwareResourceResponse(resource);
    }

    @Override
    public HardwareResourceResponse update(Long id, HardwareResourceRequest request) {
        HardwareResource existing = hardwareResourceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hardware Resource not found with ID: " + id));

        Project newProject = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + request.getProjectId()));

        existing.setName(request.getName());
        existing.setCost(request.getCost());
        existing.setSerialNumber(request.getSerialNumber());
        existing.setUnderWarranty(request.getUnderWarranty());
        existing.setAllocationDate(request.getAllocationDate());
        existing.setReturnDate(request.getReturnDate());
        existing.setProject(newProject);

        HardwareResource updatedResource = hardwareResourceRepository.save(existing);
        return mapToHardwareResourceResponse(updatedResource);
    }

    @Override
    public void delete(Long id) {
        hardwareResourceRepository.deleteById(id);
    }


}
