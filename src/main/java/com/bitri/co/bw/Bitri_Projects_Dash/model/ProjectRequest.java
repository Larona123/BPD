package com.bitri.co.bw.Bitri_Projects_Dash.model;

import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.ProjectStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class ProjectRequest {

    @NotBlank(message = "Project name is required")
    private String name;

    private Double budget;
    private String description;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull(message = "Status is required")
    private ProjectStatus status;

    private String projectManager;

    @NotNull(message = "Owner ID (Client ID) is required")
    private Long ownerId;

    @JsonProperty("owner")
    public void setOwner(Map<String, Object> ownerMap) {
        if (ownerMap != null && ownerMap.containsKey("id")) {
            Object idObject = ownerMap.get("id");
            if (idObject instanceof Integer) {
                this.ownerId = ((Integer) idObject).longValue();
            } else if (idObject instanceof Long) {
                this.ownerId = (Long) idObject;
            } else if (idObject instanceof String) {
                try {
                    this.ownerId = Long.parseLong((String) idObject);
                } catch (NumberFormatException e) {
                    this.ownerId = null;
                }
            }
        }
    }
}
