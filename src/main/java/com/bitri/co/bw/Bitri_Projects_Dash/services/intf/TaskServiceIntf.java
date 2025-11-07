package com.bitri.co.bw.Bitri_Projects_Dash.services.intf;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Task;
import com.bitri.co.bw.Bitri_Projects_Dash.model.TaskRequest;

import java.util.List;
import java.util.Optional;

public interface TaskServiceIntf {
    Task create(TaskRequest taskDto);
    Task getTaskById(Long id);
    List<Task> getAllTasks();
    Task update(Long id, TaskRequest taskDto);
    void deleteTask(Long id);
}
