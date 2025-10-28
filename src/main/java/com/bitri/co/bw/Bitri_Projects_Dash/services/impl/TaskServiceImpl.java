package com.bitri.co.bw.Bitri_Projects_Dash.services.impl;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Task;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.ProjectRepository;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.TaskRepository;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.TaskServiceIntf;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskServiceIntf {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> getById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    @Transactional
    public Task save(Task task) {
        if (task.getProjectId() == null) {
            throw new IllegalArgumentException("Project ID must be provided for the task.");
        }

        projectRepository.findById(task.getProjectId())
                .ifPresentOrElse(
                        task::setProject,
                        () -> {
                            throw new EntityNotFoundException("Project with ID " + task.getProjectId() + " not found.");
                        }
                );

        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getTasksByProjectId(Long projectId) {
        return taskRepository.findAllByProject_Id(projectId);
    }
}
