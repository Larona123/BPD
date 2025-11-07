package com.bitri.co.bw.Bitri_Projects_Dash.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class HardwareResourceRequest {
    @NotNull
    private String name;
    @NotNull
    private Double cost;
    private String serialNumber;
    private Boolean underWarranty;
    private LocalDate allocationDate;
    private LocalDate returnDate;
    @NotNull
    private Long projectId;
}
