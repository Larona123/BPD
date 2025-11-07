package com.bitri.co.bw.Bitri_Projects_Dash.services.impl;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Client;
import com.bitri.co.bw.Bitri_Projects_Dash.entity.Project;
import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.RiskAndIssueSeverity;
import com.bitri.co.bw.Bitri_Projects_Dash.model.*;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.ProjectServiceIntf;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectServiceImpl implements ProjectServiceIntf {

    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final TaskRepository taskRepository;
    private final RiskRepository riskRepository;
    private final IssueRepository issueRepository;
    private final ResourceRepository resourceRepository;
    private final HardwareResourceRepository hardwareResourceRepository;
    private final HumanResourceRepository humanResourceRepository;
    private final BudgetRepository budgetRepository;
    private final ClientRepository clientRepository;

    private ProjectResponse mapToResponseDTO(Project project) {
        Client owner = project.getOwner();

        ClientResponse ownerDTO = null;
        if (owner != null) {
            ownerDTO = ClientResponse.builder()
                    .id(owner.getId())
                    .name(owner.getName())
                    .email(owner.getEmail())
                    .company(owner.getCompany())
                    .phoneNumber(owner.getPhoneNumber())
                    .build();
        }

        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .budget(project.getBudget())
                .description(project.getDescription())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .status(project.getStatus())
                .projectManager(project.getProjectManager())
                .owner(ownerDTO)
                .build();
    }


    @Override
    public List<ProjectResponse> getAll() {
        return projectRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProjectResponse> getById(Long id) {
        return projectRepository.findById(id)
                .map(this::mapToResponseDTO);
    }


    @Override
    public ProjectResponse save(ProjectRequest requestDTO) {
        Long ownerId = requestDTO.getOwnerId();

        if (requestDTO.getStatus() == null) {
            throw new IllegalArgumentException("Project status is mandatory.");
        }

        if (ownerId == null) {
            throw new IllegalArgumentException("Project owner ID is required and must not be null.");
        }

        Client owner = clientRepository.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + ownerId));

        Project newProject = Project.builder()
                .name(requestDTO.getName())
                .budget(requestDTO.getBudget())
                .description(requestDTO.getDescription())
                .startDate(requestDTO.getStartDate())
                .endDate(requestDTO.getEndDate())
                .status(requestDTO.getStatus())
                .projectManager(requestDTO.getProjectManager())
                .owner(owner)
                .build();

        newProject.setId(null);

        Project savedProject = projectRepository.save(newProject);
        return mapToResponseDTO(savedProject);
    }

    @Override
    public ProjectResponse update(Long id, ProjectRequest requestDTO) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + id));

        existingProject.setName(requestDTO.getName());
        existingProject.setBudget(requestDTO.getBudget());
        existingProject.setDescription(requestDTO.getDescription());
        existingProject.setStartDate(requestDTO.getStartDate());
        existingProject.setEndDate(requestDTO.getEndDate());
        existingProject.setStatus(requestDTO.getStatus());
        existingProject.setProjectManager(requestDTO.getProjectManager());

        if (!requestDTO.getOwnerId().equals(existingProject.getOwner() != null ? existingProject.getOwner().getId() : null)) {
            Client newOwner = clientRepository.findById(requestDTO.getOwnerId())
                    .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + requestDTO.getOwnerId()));
            existingProject.setOwner(newOwner);
        }

        Project updatedProject = projectRepository.save(existingProject);
        return mapToResponseDTO(updatedProject);
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    public DashboardMetricsDTO getProjectDashboardMetrics() {
        DashboardMetricsDTO.DashboardMetricsDTOBuilder metricsBuilder = DashboardMetricsDTO.builder();
        metricsBuilder.totalActualExpenditure(budgetRepository.calculateTotalActualExpenditure());
        metricsBuilder.totalPersonnel(humanResourceRepository.countTotalPersonnel());
        metricsBuilder.totalEquipment(hardwareResourceRepository.countTotalEquipment());
        metricsBuilder.totalTasks(taskRepository.count());
        metricsBuilder.tasksInProgress(taskRepository.countByStatus("In Progress"));
        metricsBuilder.tasksComplete(taskRepository.countByStatus("Completed"));
        metricsBuilder.tasksNew(taskRepository.countByStatus("New"));
        metricsBuilder.totalRisks(riskRepository.count());
        metricsBuilder.risksExtreme(riskRepository.countBySeverity(RiskAndIssueSeverity.EXTREME));
        metricsBuilder.risksHigh(riskRepository.countBySeverity(RiskAndIssueSeverity.HIGH));
        metricsBuilder.risksMedium(riskRepository.countBySeverity(RiskAndIssueSeverity.MEDIUM));
        metricsBuilder.risksLow(riskRepository.countBySeverity(RiskAndIssueSeverity.LOW));
        metricsBuilder.risksSolved(riskRepository.countRisksSolved());
        metricsBuilder.totalIssues(issueRepository.count());
        metricsBuilder.issuesOpen(issueRepository.countByStatus("Open"));
        metricsBuilder.issuesClosed(issueRepository.countByStatus("Closed"));
        metricsBuilder.issuesSolved(issueRepository.countByStatus("Solved"));
        return metricsBuilder.build();
    }

    public List<?> getTasksForProject(Long projectId) {
        return List.of();
    }

    public List<ProjectStatusCountResponse> getProjectStatusCounts() {
        return projectRepository.countProjectsByStatus();
    }

    @Override
    public DashboardMetricsDTO getDashboardMetrics() {
        Long totalProjects = projectRepository.count();
        Long totalTasks = taskRepository.count();
        Long totalRisks = riskRepository.count();
        Long totalIssues = issueRepository.count();

        return DashboardMetricsDTO.builder()
                .totalProjects(totalProjects)
                .totalTasks(totalTasks)
                .totalRisks(totalRisks)
                .totalIssues(totalIssues)
                .build();
    }
}
