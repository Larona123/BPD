package com.bitri.co.bw.Bitri_Projects_Dash.services.impl;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Project;
import com.bitri.co.bw.Bitri_Projects_Dash.entity.Resource;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.ResourceRepository;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.ResourceServiceIntf;

import java.util.List;
import java.util.Optional;

@Service

public class ResourceServiceImpl implements ResourceServiceIntf {

    private final ResourceRepository resourceRepository;
    private final ProjectRepository projectRepository;

    public ResourceServiceImpl(ResourceRepository resourceRepository, ProjectRepository projectRepository) {
        this.resourceRepository = resourceRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Resource> getAll() {
        return resourceRepository.findAll();
    }

    @Override
    public Optional<Resource> getById(Long id) {
        return resourceRepository.findById(id);
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

        System.out.println("DEBUG CHECK BEFORE SAVE:");
        System.out.println("Resource ID: " + resource.getId());
        System.out.println("Resource Name: " + resource.getName());
        // This MUST print a non-null object reference
        System.out.println("Project Object Set: " + (resource.getProject() != null ? "YES (Project ID: " + resource.getProject().getId() + ")" : "NO/NULL"));
        System.out.println("Is Project transient? " + project.getId());
        // *

        return resourceRepository.save(resource);
    }

//    @Override
//    // Line 44: The save method starts here, or the check is here.
//    public Resource save(Resource resource) {
//        // --- THIS IS LIKELY WHERE THE ISSUE IS (Line 44) ---
//        if (resource.getProjectId() == null || resource.getProjectId() <= 0) {
//            throw new IllegalArgumentException("Resource creation failed: Project ID must be provided.");
//        }
//        // ... business logic
//        // return resourceRepository.save(resource);
//        return resource; // Placeholder for compilation
//    }

    @Override
    @Transactional
    public void delete(Long id) {

        resourceRepository.deleteById(id);
    }
}
