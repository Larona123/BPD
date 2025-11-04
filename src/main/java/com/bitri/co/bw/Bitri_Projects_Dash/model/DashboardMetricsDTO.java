package com.bitri.co.bw.Bitri_Projects_Dash.model;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
public class DashboardMetricsDTO {

    private Long totalProjects;

    private Double totalActualExpenditure;
    private List<ProjectStatusCountResponse> statusCounts;

    private Long totalPersonnel;
    private Long totalEquipment;
    private List<String> softwareToolsUsed;

    private Long totalTasks;
    private Long tasksInProgress;
    private Long tasksComplete;
    private Long tasksNew;

    private Long totalRisks;
    private Long risksExtreme;
    private Long risksHigh;
    private Long risksMedium;
    private Long risksLow;
    private Long risksSolved;

    private Long totalIssues;
    private Long issuesOpen;
    private Long issuesClosed;
    private Long issuesSolved;

}
