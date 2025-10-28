package com.bitri.co.bw.Bitri_Projects_Dash.services.intf;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskServiceIntf {

    List<Task> getAll();
    Optional<Task> getById(Long id);
    Task save(Task task);
    void delete(Long id);
    List<Task> getTasksByProjectId(Long projectId);
}
