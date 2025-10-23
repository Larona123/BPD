package com.bitri.co.bw.Bitri_Projects_Dash.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "hardware_resource")
public class HardwareResource {
    @Id
    private Long id;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "under_warranty")
    private Boolean underWarranty;

    @Column(name = "allocation_date")
    private LocalDate allocationDate;

    @Column(name = "return_date")
    private LocalDate returnDate;
}
