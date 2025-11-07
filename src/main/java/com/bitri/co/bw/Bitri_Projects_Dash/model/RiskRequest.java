package com.bitri.co.bw.Bitri_Projects_Dash.model;

import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.RiskAndIssueSeverity;
import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.RiskStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RiskRequest {

    private Long projectId;
    private String description;
    private RiskAndIssueSeverity severity;
    private RiskStatus status;
    private String owner;
    private LocalDate dateRaised;
    private String mitigationPlan;
}
