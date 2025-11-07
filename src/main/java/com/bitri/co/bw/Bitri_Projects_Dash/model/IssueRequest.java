package com.bitri.co.bw.Bitri_Projects_Dash.model;

import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.IssuePriority;
import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.RiskAndIssueSeverity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class IssueRequest {

        @NotBlank(message = "Title is required")
        private String title;

        @NotBlank(message = "Description is required")
        private String description;

        @NotNull(message = "Severity is required")
        private RiskAndIssueSeverity severity;

        @NotNull(message = "Priority is required")
        private IssuePriority priority;

        @NotNull(message = "Reported on is required")
        private LocalDate reportedOn;

        @NotNull(message = "Reported by is required")
        private String reportedBy;

        @NotNull(message = "Project ID is required for linkage")
        private Long projectId;
}
