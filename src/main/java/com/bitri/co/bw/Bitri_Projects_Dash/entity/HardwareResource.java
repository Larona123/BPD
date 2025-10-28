package com.bitri.co.bw.Bitri_Projects_Dash.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "hardware_resource")
public class HardwareResource extends Resource{

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "under_warranty")
    private Boolean underWarranty;

    @Column(name = "allocation_date")
    private LocalDate allocationDate;

    @Column(name = "return_date")
    private LocalDate returnDate;
}
