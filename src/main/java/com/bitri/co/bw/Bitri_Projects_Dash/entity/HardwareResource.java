package com.bitri.co.bw.Bitri_Projects_Dash.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@PrimaryKeyJoinColumn(name = "id")
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
