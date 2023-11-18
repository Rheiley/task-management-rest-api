package com.rheiley.taskmanagement.mapper;

import com.rheiley.taskmanagement.dto.TaskDto;
import com.rheiley.taskmanagement.entity.Task;

public class TaskMapper {
        public static TaskDto mapToTaskDto(Task task){
            return new TaskDto(
                    task.getId(),
                    task.getTaskName(),
                    task.getDescription(),
                    task.isCompleted()
            );
        }

        public static Task mapToTask(TaskDto taskDto){
            return new Task(
                    taskDto.getId(),
                    taskDto.getTaskName(),
                    taskDto.getDescription(),
                    taskDto.isCompleted()
            );
        }
}
