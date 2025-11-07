package com.bitri.co.bw.Bitri_Projects_Dash.model;

import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.IssuePriority;
import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.IssueStatus;
import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.RiskAndIssueSeverity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class IssueResponse {
    private Long id;
    private String title;
    private String description;
    private IssueStatus status;
    private RiskAndIssueSeverity severity;
    private IssuePriority priority;
    private LocalDate reportedOn;
    private String reportedBy;
    private ProjectResponse project;
}
