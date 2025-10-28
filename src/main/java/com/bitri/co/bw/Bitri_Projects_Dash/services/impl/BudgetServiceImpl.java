package com.bitri.co.bw.Bitri_Projects_Dash.services.impl;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Budget;
import com.bitri.co.bw.Bitri_Projects_Dash.entity.Project;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.BudgetRepository;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.ProjectRepository;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.BudgetServiceIntf;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetServiceIntf {

    private final BudgetRepository budgetRepository;
    private final ProjectRepository projectRepository;


    private Project fetchAndSetProject(Budget budget) {
        if (budget.getProjectId() == null) {
            throw new IllegalArgumentException("Project ID is mandatory for budget operations.");
        }

        Project project = projectRepository.findById(budget.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + budget.getProjectId()));

        budget.setProject(project);
        return project;
    }

    @Override
    public List<Budget> getAll() {
        return budgetRepository.findAll();
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
    public Budget save(Budget budget) {
        fetchAndSetProject(budget);
        budget.setId(null);
        return budgetRepository.save(budget);
    }



    @Override
    @Transactional
    public Budget update(Long id, Budget updatedBudget) {
        Budget existingBudget = budgetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Budget not found with ID: " + id));

        existingBudget.setPlannedHours(updatedBudget.getPlannedHours());
        existingBudget.setActualHours(updatedBudget.getActualHours());
        existingBudget.setPlannedExpenditure(updatedBudget.getPlannedExpenditure());
        existingBudget.setActualExpenditure(updatedBudget.getActualExpenditure());
        existingBudget.setPlannedTimeDuration(updatedBudget.getPlannedTimeDuration());
        existingBudget.setActualTimeDuration(updatedBudget.getActualTimeDuration());
        existingBudget.setPlannedBudget(updatedBudget.getPlannedBudget());
        existingBudget.setActualBudget(updatedBudget.getActualBudget());
        existingBudget.setPercentSpent(updatedBudget.getPercentSpent());
        existingBudget.setRemainingMoney(updatedBudget.getRemainingMoney());
        existingBudget.setStatus(updatedBudget.getStatus());
        existingBudget.setNotes(updatedBudget.getNotes());


        if (updatedBudget.getProjectId() != null && !updatedBudget.getProjectId().equals(existingBudget.getProject().getId())) {
            existingBudget.setProjectId(updatedBudget.getProjectId());
            fetchAndSetProject(existingBudget);
        } else if (existingBudget.getProject() == null) {
            throw new IllegalArgumentException("Project ID must be provided to link or maintain the project.");
        }
        return budgetRepository.save(existingBudget);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        budgetRepository.deleteById(id);
    }
}
