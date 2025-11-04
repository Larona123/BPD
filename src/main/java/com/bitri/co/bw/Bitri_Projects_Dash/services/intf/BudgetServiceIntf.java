package com.bitri.co.bw.Bitri_Projects_Dash.services.intf;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Budget;

import java.util.List;
import java.util.Optional;

public interface BudgetServiceIntf {
    List<Budget> getAll();
    Optional<Budget> getById(Long id);
    Budget save(Budget budget);
    Budget update(Long id, Budget budget);
    void delete(Long id);
    Optional<Budget> getBudgetByProjectId(Long projectId);

    Double calculateTotalExpenditure();
}
