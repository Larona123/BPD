package com.bitri.co.bw.Bitri_Projects_Dash.controller;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Task;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.TaskServiceIntf;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskServiceIntf taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Task>> getTasksByProjectId(@PathVariable Long projectId) {
        return ResponseEntity.ok(taskService.getTasksByProjectId(projectId));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        // The service layer handles validation and linking to the Project entity
        Task savedTask = taskService.save(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        return taskService.getById(id)
                .map(existingTask -> {
                    // Update fields
                    existingTask.setTaskDescription(taskDetails.getTaskDescription());
                    existingTask.setAssignee(taskDetails.getAssignee());
                    existingTask.setPriority(taskDetails.getPriority());
                    existingTask.setStatus(taskDetails.getStatus());
                    existingTask.setDueDate(taskDetails.getDueDate());

                    // Crucially, use the ProjectId from the request body if present,
                    // which the service layer will use to update the FK link.
                    if (taskDetails.getProjectId() != null) {
                        existingTask.setProjectId(taskDetails.getProjectId());
                    }

                    Task updatedTask = taskService.save(existingTask);
                    return ResponseEntity.ok(updatedTask);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (taskService.getById(id).isPresent()) {
            taskService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
