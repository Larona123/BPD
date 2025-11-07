package com.bitri.co.bw.Bitri_Projects_Dash.services.impl;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Client;
import com.bitri.co.bw.Bitri_Projects_Dash.entity.Project;
import com.bitri.co.bw.Bitri_Projects_Dash.entity.Resource;
import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.ProjectStatus;
import com.bitri.co.bw.Bitri_Projects_Dash.model.ClientResponse;
import com.bitri.co.bw.Bitri_Projects_Dash.model.HardwareResourceResponse;
import com.bitri.co.bw.Bitri_Projects_Dash.model.ProjectResponse;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.ResourceRepository;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.ResourceServiceIntf;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ResourceServiceImpl implements ResourceServiceIntf {

    private final ResourceRepository resourceRepository;
    private final ProjectRepository projectRepository;
    @Override
    @Transactional(readOnly = true)
    public List<HardwareResourceResponse> getAll() {
        return resourceRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<HardwareResourceResponse> getById(Long id) {
        return resourceRepository.findById(id)
                .map(this::toDto);
    }

    @Override
    @Transactional
    public Resource save(Resource resource) {
        Long projectId = resource.getProjectInputId();

        if (projectId == null  || projectId <= 0) {
            throw new IllegalArgumentException("Resource creation failed: Project ID must be provided.");
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + projectId));

        resource.setProject(project);

        return resourceRepository.save(resource);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        resourceRepository.deleteById(id);
    }
    private HardwareResourceResponse toDto(Resource entity) {
        if (entity == null) {
            return null;
        }

        ProjectResponse projectDto = toProjectDto(entity.getProject());

        return HardwareResourceResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cost(entity.getCost())
                .type(entity.getType())
                .projectId(entity.getProjectInputId())
                .project(projectDto)
                .build();
    }

    private ProjectResponse toProjectDto(Project entity) {
        if (entity == null) {
            return null;
        }
        ClientResponse ownerDto = toOwnerDto(entity.getOwner());

        return ProjectResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .budget(entity.getBudget())
                .description(entity.getDescription())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .status(entity.getStatus() != null ? ProjectStatus.valueOf(entity.getStatus().toString()) : null)
                .projectManager(entity.getProjectManager())
                .owner(ownerDto)
                .build();
    }

    private ClientResponse toOwnerDto(Client entity) {
        if (entity == null) {
            return null;
        }
        return ClientResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .company(entity.getCompany())
                .phoneNumber(entity.getPhoneNumber())
                .build();
    }
}
