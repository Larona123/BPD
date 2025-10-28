package com.bitri.co.bw.Bitri_Projects_Dash.controller;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Budget;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.BudgetServiceIntf;
import jakarta.persistence.EntityNotFoundException;
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

    /**
     * Retrieves all budget entries.
     */
    @GetMapping
    public ResponseEntity<List<Budget>> getAllBudgets() {
        return ResponseEntity.ok(budgetService.getAll());
    }

    /**
     * Retrieves a single budget by its ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable Long id) {
        return budgetService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Retrieves the budget associated with a specific Project ID.
     */
    @GetMapping("/project/{projectId}")
    public ResponseEntity<Budget> getBudgetByProjectId(@PathVariable Long projectId) {
        return budgetService.getBudgetByProjectId(projectId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new budget entry.
     * The Budget entity must contain a valid 'projectId' in its transient field.
     */
    @PostMapping
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
        try {
            Budget savedBudget = budgetService.save(budget);
            return new ResponseEntity<>(savedBudget, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Handles missing projectId or other invalid data
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException e) {
            // Handles case where the referenced Project ID doesn't exist
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Updates an existing budget entry.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long id,
                                               @RequestBody Budget budget) {
        try {
            Budget updatedBudget = budgetService.update(id, budget);
            return ResponseEntity.ok(updatedBudget);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        if (budgetService.getById(id).isPresent()) {
            budgetService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
