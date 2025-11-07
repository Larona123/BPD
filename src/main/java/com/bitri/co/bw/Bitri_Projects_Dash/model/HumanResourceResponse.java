package com.bitri.co.bw.Bitri_Projects_Dash.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class HumanResourceResponse extends ResourceResponse{
    private String role;
    private Double hourlyRate;
    private Double hoursAllocated;
    private LocalDate startDate;
    private LocalDate endDate;
}
