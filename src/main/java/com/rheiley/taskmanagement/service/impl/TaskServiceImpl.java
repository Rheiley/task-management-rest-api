package com.rheiley.taskmanagement.service.impl;

import com.rheiley.taskmanagement.dto.TaskDto;
import com.rheiley.taskmanagement.entity.Task;
import com.rheiley.taskmanagement.exception.TaskNotFoundException;
import com.rheiley.taskmanagement.mapper.TaskMapper;
import com.rheiley.taskmanagement.repository.TaskRepository;
import com.rheiley.taskmanagement.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{
    private TaskRepository taskRepository;

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Task task = TaskMapper.mapToTask(taskDto);

        task = taskRepository.save(task);

        return TaskMapper.mapToTaskDto(task);
    }

    @Override
    public TaskDto getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new TaskNotFoundException("Task with id " + taskId + " not found.")
        );

        return TaskMapper.mapToTaskDto(task);
    }

    @Override
    public TaskDto updateTask(Long taskId, TaskDto updatedTask) {
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new TaskNotFoundException("Task with id " + taskId + " not found.")
        );

        task.setTaskName(updatedTask.getTaskName());
        task.setDescription(updatedTask.getDescription());
        task.setCompleted(updatedTask.isCompleted());

        task = taskRepository.save(task);

        return TaskMapper.mapToTaskDto(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.findById(taskId).orElseThrow(
                () -> new TaskNotFoundException("Task with id " + taskId + " not found.")
        );

        taskRepository.deleteById(taskId);
    }
}
