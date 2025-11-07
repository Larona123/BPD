package com.bitri.co.bw.Bitri_Projects_Dash.model;

import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.ProjectStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ProjectResponse {
    private Long id;
    private String name;
    private Double budget;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String projectManager;
    private ProjectStatus status;
    private ClientResponse owner;
}
