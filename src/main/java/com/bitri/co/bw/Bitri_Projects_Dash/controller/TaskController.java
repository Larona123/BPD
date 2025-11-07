package com.bitri.co.bw.Bitri_Projects_Dash.controller;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Task;
import com.bitri.co.bw.Bitri_Projects_Dash.model.TaskRequest;
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
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody TaskRequest taskDto) {
        Task createdTask = taskService.create(taskDto);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody TaskRequest taskDto) {
        Task updatedTask = taskService.update(id, taskDto);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
