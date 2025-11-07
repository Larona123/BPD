package com.bitri.co.bw.Bitri_Projects_Dash.services.impl;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Project;
import com.bitri.co.bw.Bitri_Projects_Dash.entity.Task;
import com.bitri.co.bw.Bitri_Projects_Dash.enumeration.TaskStatus;
import com.bitri.co.bw.Bitri_Projects_Dash.model.TaskRequest;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.ProjectRepository;
import com.bitri.co.bw.Bitri_Projects_Dash.repository.TaskRepository;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.TaskServiceIntf;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskServiceIntf {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    private Project getProjectEntity(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + projectId));
    }

    @Override
    public Task create(TaskRequest taskDto) {
        Project project = getProjectEntity(taskDto.getProjectId());

        Task task = new Task();
        task.setTaskDescription(taskDto.getTaskDescription());
        task.setAssignee(taskDto.getAssignee());
        task.setAssignedBy(taskDto.getAssignedBy());
        task.setPriority(taskDto.getPriority());
        task.setDueDate(taskDto.getDueDate());

        task.setStatus(taskDto.getStatus() != null ? taskDto.getStatus() : TaskStatus.TO_DO);

        task.setDateAssigned(LocalDate.now());
        task.setProject(project);

        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID: " + id));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task update(Long id, TaskRequest taskDto) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID: " + id));

        if (!existingTask.getProject().getId().equals(taskDto.getProjectId())) {
            Project newProject = getProjectEntity(taskDto.getProjectId());
            existingTask.setProject(newProject);
        }

        existingTask.setTaskDescription(taskDto.getTaskDescription());
        existingTask.setAssignee(taskDto.getAssignee());
        existingTask.setAssignedBy(taskDto.getAssignedBy());
        existingTask.setPriority(taskDto.getPriority());
        existingTask.setStatus(taskDto.getStatus());
        existingTask.setDueDate(taskDto.getDueDate());

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Task not found with ID: " + id);
        }
        taskRepository.deleteById(id);

    }
}
