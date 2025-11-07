package com.bitri.co.bw.Bitri_Projects_Dash.controller;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Budget;
import com.bitri.co.bw.Bitri_Projects_Dash.model.BudgetRequest;
import com.bitri.co.bw.Bitri_Projects_Dash.model.BudgetResponse;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.BudgetServiceIntf;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/budgets")
public class BudgetController {

    private final BudgetServiceIntf budgetService;
    @GetMapping
    public ResponseEntity<List<BudgetResponse>> getAllBudgets() {
        List<BudgetResponse> budgets = budgetService.getAllBudgetsDto();
        return ResponseEntity.ok(budgets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetResponse> getBudgetById(@PathVariable Long id) {
        BudgetResponse budget = budgetService.getBudgetByIdDto(id);
        return ResponseEntity.ok(budget);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<BudgetResponse> getBudgetByProjectId(@PathVariable Long projectId) {
        BudgetResponse budget = budgetService.getBudgetByProjectIdDto(projectId);
        return ResponseEntity.ok(budget);
    }

    @PostMapping
    public ResponseEntity<BudgetResponse> create(@Valid @RequestBody BudgetRequest budgetRequest) {
        BudgetResponse savedBudget = budgetService.save(budgetRequest);
        return new ResponseEntity<>(savedBudget, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetResponse> update(@PathVariable Long id, @Valid @RequestBody BudgetRequest budgetRequest) {
        BudgetResponse updatedBudget = budgetService.update(id, budgetRequest);
        return ResponseEntity.ok(updatedBudget);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        budgetService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total-expenditure")
    public ResponseEntity<Double> getTotalExpenditure() {
        Double total = budgetService.calculateTotalExpenditure();
        return ResponseEntity.ok(total);
    }

}
