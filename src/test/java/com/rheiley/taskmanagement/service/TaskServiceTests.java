package com.rheiley.taskmanagement.service;

import com.rheiley.taskmanagement.dto.*;
import com.rheiley.taskmanagement.entity.*;
import com.rheiley.taskmanagement.mapper.*;
import com.rheiley.taskmanagement.repository.*;
import com.rheiley.taskmanagement.service.impl.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.*;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTests {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    public void createTaskShouldReturnTaskDto(){
        Task task = new Task(1L, "taskName", "description", false, "123");
        TaskDto taskDto = TaskMapper.mapToTaskDto(task);

        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(task);

        TaskDto returnedTaskDto = taskService.createTask(taskDto, "123");

        assertEquals(taskDto, returnedTaskDto);
    }

    @Test
    public void getTaskByIdShouldReturnTaskDto(){
        Long taskId = 1L;
        Task task = new Task(taskId, "taskName", "description", false, "123");

        TaskDto taskDto = TaskMapper.mapToTaskDto(task);

        Mockito.when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        TaskDto returnedTaskDto = taskService.getTaskById(taskId);

        assertEquals(taskDto, returnedTaskDto);
    }

    @Test
    public void updateTaskShouldReturnUpdatedTaskDto(){
        Long taskId = 1L;

        Task existingTask = new Task(taskId, "currentTaskName", "currentDescription", false, "123");

        Task updatedTask = new Task(taskId, "updatedTaskName", "updatedDescription", true, "123");
        TaskDto updatedTaskDto = TaskMapper.mapToTaskDto(updatedTask);

        Mockito.when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenAnswer(
                invocation -> invocation.getArgument(0) // retrieves the Task object passed to the save method in taskRepository
        );

        TaskDto returnedUpdatedTaskDto = taskService.updateTask(taskId, updatedTaskDto);

        assertEquals(returnedUpdatedTaskDto, updatedTaskDto);
    }

    @Test
    public void deleteTaskShouldDeleteTask(){
        Long taskId = 1L;

        Task task = new Task(taskId, "taskName", "description", false, "123");

        Mockito.when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        Mockito.doNothing().when(taskRepository).deleteById(taskId);

        assertAll(() -> taskService.deleteTask(taskId)); // asserts that this invocation does not throw an exception
    }

}