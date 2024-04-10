package com.api.repositories;

import com.api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<Task> findById(Long id);
    List<Task> findAllByIsCompleted(boolean isCompleted);

    @Query("SELECT t FROM Task t JOIN t.user u WHERE u.username = :username AND t.isCompleted = true")
    List<Task> findCompletedTaskByUsername(@Param("username") String username);

    @Query("SELECT t FROM Task t JOIN t.user u WHERE u.username = :username AND t.isCompleted = false")
    List<Task> findInCompletedTaskByUsername(@Param("username") String username);
}