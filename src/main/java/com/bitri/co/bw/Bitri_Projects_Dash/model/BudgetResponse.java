package com.bitri.co.bw.Bitri_Projects_Dash.model;

import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.BudgetStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BudgetResponse {

    private Long id;
    private Double plannedHours;
    private Double actualHours;
    private Double plannedExpenditure;
    private Double actualExpenditure;
    private Double plannedTimeDuration;
    private Double actualTimeDuration;
    private Double plannedBudget;
    private Double actualBudget;
    private Double percentSpent;
    private Double remainingMoney;
    private BudgetStatus status;
    private String notes;
    private ProjectResponse project;
}
