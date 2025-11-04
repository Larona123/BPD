package com.bitri.co.bw.Bitri_Projects_Dash.services.impl;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Project;
import com.bitri.co.bw.Bitri_Projects_Dash.entity.User;
import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.ProjectStatus;
import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.RiskAndIssueSeverity;
import com.bitri.co.bw.Bitri_Projects_Dash.model.DashboardMetricsDTO;
import com.bitri.co.bw.Bitri_Projects_Dash.model.ProjectResponse;
import com.bitri.co.bw.Bitri_Projects_Dash.model.ProjectStatusCountResponse;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.ProjectServiceIntf;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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

    private ProjectResponse toResponseDto(Project project) {
        ProjectResponse dto = new ProjectResponse();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setStatus(project.getStatus());

        if (project.getOwner() != null) {
            dto.setOwnerId(project.getOwner().getId());
            dto.setOwnerName(project.getOwner().getName());
        }
        return dto;
    }

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, UserService userService,
                              TaskRepository taskRepository,
                              RiskRepository riskRepository,
                              IssueRepository issueRepository,
                              HumanResourceRepository humanResourceRepository,
                              HardwareResourceRepository hardwareResourceRepository,
                              ResourceRepository resourceRepository, BudgetRepository budgetRepository) {
        this.projectRepository = projectRepository;
        this.userService = userService;
        this.taskRepository = taskRepository;
        this.riskRepository = riskRepository;
        this.issueRepository = issueRepository;
        this.humanResourceRepository = humanResourceRepository;
        this.hardwareResourceRepository = hardwareResourceRepository;
        this.resourceRepository = resourceRepository;
        this.budgetRepository = budgetRepository;
    }

    public DashboardMetricsDTO getProjectDashboardMetrics() {
        DashboardMetricsDTO.DashboardMetricsDTOBuilder metricsBuilder = DashboardMetricsDTO.builder();

        metricsBuilder.totalActualExpenditure(budgetRepository.calculateTotalActualExpenditure());

        metricsBuilder.totalPersonnel(humanResourceRepository.countTotalPersonnel());
        metricsBuilder.totalEquipment(hardwareResourceRepository.countTotalEquipment());
//        metricsBuilder.softwareToolsUsed(resourceRepository.findDistinctSoftwareToolsUsed());

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
        // Implementation for project-specific tasks would go here
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

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> getById(Long id) {
        return projectRepository.findById(id);

    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
