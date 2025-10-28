package com.bitri.co.bw.Bitri_Projects_Dash.entity;

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
@Table(name = "risk")
public class Risk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private RiskAndIssueSeverity severity;

    private String owner;

    @Column(name = "date_raised")
    private LocalDate dateRaised;

    @Column(name = "mitigation_plan")
    private String mitigationPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id" , nullable = false)
    @JsonIgnore
    private Project project;
}
