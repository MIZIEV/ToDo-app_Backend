package com.api.repositories;

import com.api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Task findByTaskUniqueKey(String taskUniqueKey);

    Task findByName(String todoName);
    List<Task> findAllByIsCompleted(boolean isCompleted);
}