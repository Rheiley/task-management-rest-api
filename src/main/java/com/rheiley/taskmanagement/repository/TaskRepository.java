package com.rheiley.taskmanagement.repository;

import com.rheiley.taskmanagement.entity.Task;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
    List<Task> getTasksByUserUid(String userUid);
}
