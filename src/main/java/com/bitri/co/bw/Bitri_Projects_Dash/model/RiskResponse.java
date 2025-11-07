package com.bitri.co.bw.Bitri_Projects_Dash.model;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Project;
import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.RiskAndIssueSeverity;
import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.RiskStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RiskResponse {

    private Long id;
    private String description;
    private RiskAndIssueSeverity severity;
    private RiskStatus status;
    private String owner;
    private LocalDate dateRaised;
    private String mitigationPlan;
    private Project project;
}
