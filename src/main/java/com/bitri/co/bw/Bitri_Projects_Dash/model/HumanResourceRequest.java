package com.bitri.co.bw.Bitri_Projects_Dash.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class HumanResourceRequest {
    @NotNull
    private String name;
    @NotNull
    private Double cost;
    private String role;
    private Double hourlyRate;
    private Double hoursAllocated;
    private LocalDate startDate;
    private LocalDate endDate;
    @NotNull
    private Long projectId;
}
