package com.bitri.co.bw.Bitri_Projects_Dash.entity;

import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.IssuePriority;
import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.TaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String taskDescription;

    @Column(nullable = false)
    private String assignee;

    @Column(name = "assigned_by", nullable = false)
    private String assignedBy;

    @Column(name = "date_assigned", nullable = false)
    private LocalDate dateAssigned = LocalDate.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IssuePriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.TO_DO;

    @Column(name = "due_date")
    private LocalDate dueDate;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)

    private Project project;

}
