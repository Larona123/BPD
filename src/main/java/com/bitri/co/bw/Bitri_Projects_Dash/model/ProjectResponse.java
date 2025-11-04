package com.bitri.co.bw.Bitri_Projects_Dash.model;

import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.ProjectStatus;
import lombok.Data;

@Data
public class ProjectResponse {
    private Long id;
    private String name;
    private String description;
    private String projectManager;
    private ProjectStatus status;
    private Long ownerId;
    private String ownerName;
}
