package com.bitri.co.bw.Bitri_Projects_Dash.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceRequest {

    private double plannedExpenditure;
    private double actualExpenditure;
    private Integer plannedSchedule;
    private Integer actualSchedule;
    private Long projectId;
}
