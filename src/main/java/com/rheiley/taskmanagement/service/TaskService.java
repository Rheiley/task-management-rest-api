package com.rheiley.taskmanagement.service;

import com.rheiley.taskmanagement.dto.TaskDto;

public interface TaskService {
    TaskDto createTask(TaskDto taskDto);

    TaskDto getTaskById(Long taskId);

    TaskDto updateTask(Long taskId, TaskDto updatedTask);

    void deleteTask(Long taskId);
}
