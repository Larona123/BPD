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
@Table(name = "human_resource")
public class HumanResource {

    @Id
    private Long id;
    private String role;

    @Column(name = "hourly_rate")
    private Double hourlyRate;

    @Column(name = "hours_allocated")
    private Double hoursAllocated;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;
}
