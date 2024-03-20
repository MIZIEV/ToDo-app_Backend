package com.api.repositories;

import com.api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<Task> findById(Long id);
    Task findByName(String todoName);
    List<Task> findAllByIsCompleted(boolean isCompleted);
}