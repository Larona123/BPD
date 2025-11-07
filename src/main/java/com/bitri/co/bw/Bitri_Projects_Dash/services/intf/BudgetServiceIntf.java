package com.bitri.co.bw.Bitri_Projects_Dash.services.intf;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Budget;
import com.bitri.co.bw.Bitri_Projects_Dash.model.BudgetRequest;
import com.bitri.co.bw.Bitri_Projects_Dash.model.BudgetResponse;

import java.util.List;
import java.util.Optional;

public interface BudgetServiceIntf {
    List<BudgetResponse> getAll();
    Optional<Budget> getById(Long id);
    BudgetResponse save(BudgetRequest request);
    BudgetResponse update(Long id, BudgetRequest request);
    void delete(Long id);
    Optional<Budget> getBudgetByProjectId(Long projectId);

    Double calculateTotalExpenditure();

    List<BudgetResponse> getAllBudgetsDto();
    BudgetResponse getBudgetByIdDto(Long id);
    BudgetResponse getBudgetByProjectIdDto(Long projectId);
}
