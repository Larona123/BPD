package com.bitri.co.bw.Bitri_Projects_Dash.entity;

import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.IssuePriority;
import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.IssueStatus;
import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.RiskAndIssueSeverity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "issue")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private IssueStatus status;

    @Enumerated(EnumType.STRING)
    private RiskAndIssueSeverity severity;

    @Enumerated(EnumType.STRING)
    private IssuePriority priority;

    @Column(name = "reported_on")
    private LocalDate reportedOn;

    @Column(name = "reported_by")
    private String reportedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
