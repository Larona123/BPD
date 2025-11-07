package com.bitri.co.bw.Bitri_Projects_Dash.entity;

import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.BudgetStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "planned_hours")
    private Double plannedHours;
    @Column(name = "actual_hours")
    private Double actualHours;

    @Column(name = "planned_expenditure")
    private Double plannedExpenditure;
    @Column(name = "actual_expenditure")
    private Double actualExpenditure;

    @Column(name = "planned_time_duration")
    private Double plannedTimeDuration;
    @Column(name = "actual_time_duration")
    private Double actualTimeDuration;

    @Column(name = "planned_budget")
    private Double plannedBudget;
    @Column(name = "actual_budget")
    private Double actualBudget;

    @Column(name = "percent_spent")
    private Double percentSpent;

    @Column(name = "Remaining")
    private Double remainingMoney;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BudgetStatus status;

    @Column(name = "notes", length = 1000)
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
