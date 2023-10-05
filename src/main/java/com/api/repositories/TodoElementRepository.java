package com.api.repositories;

import com.api.model.TodoElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoElementRepository extends JpaRepository<TodoElement, Long> {
}