package com.rheiley.taskmanagement.service;

import com.rheiley.taskmanagement.dto.TaskDto;
import com.rheiley.taskmanagement.entity.Task;

import java.util.List;

public interface TaskService {
    TaskDto createTask(TaskDto taskDto);

    TaskDto getTaskById(Long taskId);

    TaskDto updateTask(Long taskId, TaskDto updatedTask);

    List<Task> getTasksByUserUid(String userUid);

    void deleteTask(Long taskId);
}
