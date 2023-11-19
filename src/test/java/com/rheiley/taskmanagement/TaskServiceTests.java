package com.rheiley.taskmanagement;

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
        Task task = new Task(1L, "taskName", "description", false);
        TaskDto taskDto = TaskMapper.mapToTaskDto(task);

        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(task);

        TaskDto returnedTaskDto = taskService.createTask(taskDto);

        assertEquals(taskDto, returnedTaskDto);
    }

    @Test
    public void getTaskByIdShouldReturnTaskDto(){
        Long taskId = 1L;
        Task task = new Task(1L, "taskName", "description", false);

        TaskDto taskDto = TaskMapper.mapToTaskDto(task);

        Mockito.when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        TaskDto returnedTaskDto = taskService.getTaskById(taskId);

        assertEquals(taskDto, returnedTaskDto);
    }

    @Test
    public void updateTaskShouldReturnUpdatedTaskDto(){

    }
}
