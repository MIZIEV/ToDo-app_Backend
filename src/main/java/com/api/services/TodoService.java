package com.api.services;

import com.api.model.Todo;

public interface TodoService {

    void saveTodo(Todo todo);

    Todo getTodoById(Long id);

    void changeCompleteStatus(Long id);

    void deleteTodo(Long id);
}