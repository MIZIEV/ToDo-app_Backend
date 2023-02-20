package com.api.repositories;

import com.api.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodosRepository extends JpaRepository<Todo, Integer> {
    Todo findByTodoUniqueKey(String todoUniqueKey);
    List<Todo> findAllByIsCompleted(boolean isCompleted);
}