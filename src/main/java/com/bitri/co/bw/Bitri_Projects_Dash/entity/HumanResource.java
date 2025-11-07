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
@Table(name = "human_resource")
@JsonInclude(JsonInclude.Include.NON_NULL)
@PrimaryKeyJoinColumn(name = "id")
public class HumanResource extends Resource {

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
