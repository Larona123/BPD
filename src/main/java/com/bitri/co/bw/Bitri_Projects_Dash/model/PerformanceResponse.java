package com.bitri.co.bw.Bitri_Projects_Dash.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PerformanceResponse {

    private Long id;
    private Double plannedExpenditure;
    private Double actualExpenditure;
    private Integer plannedSchedule;
    private Integer actualSchedule;
    private ProjectResponse project;
}
