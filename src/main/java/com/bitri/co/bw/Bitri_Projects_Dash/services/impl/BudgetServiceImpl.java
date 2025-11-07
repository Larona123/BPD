package com.bitri.co.bw.Bitri_Projects_Dash.services.impl;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Budget;
import com.bitri.co.bw.Bitri_Projects_Dash.entity.Client;
import com.bitri.co.bw.Bitri_Projects_Dash.entity.Project;
import com.bitri.co.bw.Bitri_Projects_Dash.model.BudgetRequest;
import com.bitri.co.bw.Bitri_Projects_Dash.model.BudgetResponse;
import com.bitri.co.bw.Bitri_Projects_Dash.model.ClientResponse;
import com.bitri.co.bw.Bitri_Projects_Dash.model.ProjectResponse;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.BudgetRepository;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.ProjectRepository;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.BudgetServiceIntf;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetServiceIntf {

    private final BudgetRepository budgetRepository;
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

    private BudgetResponse mapBudgetToResponseDTO(Budget budget) {
        ProjectResponse projectResponse = budget.getProject() != null ?
                mapProjectToResponseDTO(budget.getProject()) :
                null;

        return BudgetResponse.builder()
                .id(budget.getId())
                .plannedHours(budget.getPlannedHours())
                .actualHours(budget.getActualHours())
                .plannedExpenditure(budget.getPlannedExpenditure())
                .actualExpenditure(budget.getActualExpenditure())
                .plannedTimeDuration(budget.getPlannedTimeDuration())
                .actualTimeDuration(budget.getActualTimeDuration())
                .plannedBudget(budget.getPlannedBudget())
                .actualBudget(budget.getActualBudget())
                .percentSpent(budget.getPercentSpent())
                .remainingMoney(budget.getRemainingMoney())
                .status(budget.getStatus())
                .notes(budget.getNotes())
                .project(projectResponse)
                .build();
    }

    private Budget mapRequestToEntity(BudgetRequest request, Project project) {
        Budget budget = new Budget();
        budget.setPlannedHours(request.getPlannedHours());
        budget.setActualHours(request.getActualHours());
        budget.setPlannedExpenditure(request.getPlannedExpenditure());
        budget.setActualExpenditure(request.getActualExpenditure());
        budget.setPlannedTimeDuration(request.getPlannedTimeDuration());
        budget.setActualTimeDuration(request.getActualTimeDuration());
        budget.setPlannedBudget(request.getPlannedBudget());
        budget.setActualBudget(request.getActualBudget());
        budget.setPercentSpent(request.getPercentSpent());
        budget.setRemainingMoney(request.getRemainingMoney());
        budget.setStatus(request.getStatus());
        budget.setNotes(request.getNotes());
        budget.setProject(project);
        return budget;
    }

    private Project fetchProjectById(Long projectId) {
        if (projectId == null) {
            throw new IllegalArgumentException("Project ID must be provided to link a budget.");
        }
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + projectId));
    }

    @Override
    public List<BudgetResponse> getAllBudgetsDto() {
        return budgetRepository.findAll().stream()
                .map(this::mapBudgetToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BudgetResponse getBudgetByIdDto(Long id) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Budget not found with ID: " + id));
        return mapBudgetToResponseDTO(budget);
    }

    @Override
    public BudgetResponse getBudgetByProjectIdDto(Long projectId) {
        Budget budget = budgetRepository.findByProject_Id(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Budget not found for Project ID: " + projectId));
        return mapBudgetToResponseDTO(budget);
    }

    private Project fetchAndSetProject(Budget budget) {
        if (budget.getProject() == null || budget.getProject().getId() == null) {
            throw new IllegalArgumentException("Project ID is mandatory for budget operations.");
        }

        Project project = projectRepository.findById(budget.getProject().getId())
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + budget.getProject().getId()));

        budget.setProject(project);
        return project;
    }
    @Override
    public List<BudgetResponse> getAll() {
        return null;
    }

    @Override
    public Optional<Budget> getById(Long id) {
        return budgetRepository.findById(id);
    }

    @Override
    public Optional<Budget> getBudgetByProjectId(Long projectId) {
        return budgetRepository.findByProject_Id(projectId);
    }

    @Override
    @Transactional
    public BudgetResponse save(BudgetRequest request) {
        Project project = fetchProjectById(request.getProjectId());
        Budget newBudget = mapRequestToEntity(request, project);
        Budget savedBudget = budgetRepository.save(newBudget);
        return mapBudgetToResponseDTO(savedBudget);
    }

    @Override
    @Transactional
    public BudgetResponse update(Long id, BudgetRequest request) {
        Budget existingBudget = budgetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Budget not found with ID: " + id));

        existingBudget.setPlannedHours(request.getPlannedHours());
        existingBudget.setActualHours(request.getActualHours());
        existingBudget.setPlannedExpenditure(request.getPlannedExpenditure());
        existingBudget.setActualExpenditure(request.getActualExpenditure());
        existingBudget.setPlannedTimeDuration(request.getPlannedTimeDuration());
        existingBudget.setActualTimeDuration(request.getActualTimeDuration());
        existingBudget.setPlannedBudget(request.getPlannedBudget());
        existingBudget.setActualBudget(request.getActualBudget());
        existingBudget.setPercentSpent(request.getPercentSpent());
        existingBudget.setRemainingMoney(request.getRemainingMoney());
        existingBudget.setStatus(request.getStatus());
        existingBudget.setNotes(request.getNotes());

        Long newProjectId = request.getProjectId() != null ? request.getProjectId() : null;
        Long currentProjectId = existingBudget.getProject() != null ? existingBudget.getProject().getId() : null;

        if (newProjectId != null && !newProjectId.equals(currentProjectId)) {
            Project newProject = fetchProjectById(newProjectId);
            existingBudget.setProject(newProject);
        } else if (currentProjectId == null) {
            throw new IllegalArgumentException("Project ID must be provided to link or maintain the project association.");
        }


        Budget updatedBudget = budgetRepository.save(existingBudget);
        return mapBudgetToResponseDTO(updatedBudget);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        budgetRepository.deleteById(id);
    }

    @Override
    public Double calculateTotalExpenditure() {
        return budgetRepository.calculateTotalActualExpenditure();
    }
}
