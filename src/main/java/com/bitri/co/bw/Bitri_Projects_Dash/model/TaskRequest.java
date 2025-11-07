package com.bitri.co.bw.Bitri_Projects_Dash.model;

import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.IssuePriority;
import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.TaskStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {
    private String taskDescription;
    private String assignee;
    private String assignedBy;
    private IssuePriority priority;
    private TaskStatus status;
    private LocalDate dueDate;
    private Long projectId;

}
