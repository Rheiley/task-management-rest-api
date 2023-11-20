package com.rheiley.taskmanagement.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.rheiley.taskmanagement.entity.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void createTaskShouldCreateTask(){
        Task task = new Task(1L, "taskName", "description", false);

        Task createdTask = taskRepository.save(task);

        assertNotNull(createdTask);
        assertEquals(task, createdTask);
    }

    @Test
    public void findByIdShouldReturnCorrespondingTask(){
        Long taskId = 1L;

        Task task = new Task(taskId, "taskName", "description", false);

        taskRepository.save(task);

        Task retrievedTask = taskRepository.findById(taskId).get();

        assertNotNull(retrievedTask);
        assertEquals(task, retrievedTask);
    }
}
