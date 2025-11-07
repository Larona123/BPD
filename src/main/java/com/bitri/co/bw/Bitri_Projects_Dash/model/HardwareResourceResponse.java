package com.bitri.co.bw.Bitri_Projects_Dash.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class HardwareResourceResponse extends ResourceResponse {
    private String serialNumber;
    private Boolean underWarranty;
    private LocalDate allocationDate;
    private LocalDate returnDate;

}
