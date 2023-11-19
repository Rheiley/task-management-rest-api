package com.rheiley.taskmanagement.controller;

import com.rheiley.taskmanagement.dto.TaskDto;
import com.rheiley.taskmanagement.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto){
        TaskDto task = taskService.createTask(taskDto);

        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") Long taskId){
        TaskDto taskDto = taskService.getTaskById(taskId);

        return ResponseEntity.ok(taskDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable("id") Long taskId, @RequestBody TaskDto updatedTask){
        TaskDto taskDto = taskService.updateTask(taskId, updatedTask);

        return ResponseEntity.ok(taskDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long taskId){
        taskService.deleteTask(taskId);

        return ResponseEntity.noContent().build(); // returns 204 no content
    }
}
