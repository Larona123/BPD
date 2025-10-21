package com.bitri.co.bw.Bitri_Projects_Dash.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "performance")
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "planned_expenditure")
    private Double plannedExpenditure;

    @Column(name = "actual_expenditure")
    private Double actualExpenditure;

    @Column(name = "planned_schedule")
    private Integer plannedSchedule;

    @Column(name = "actual_schedule")
    private Integer actualSchedule;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id") // Added JoinColumn for explicit OneToOne mapping
    private Project project;
}
