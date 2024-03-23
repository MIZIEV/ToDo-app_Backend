package com.api.services;

import com.api.model.Todo;

import java.util.List;

public interface TodoService {

    void saveTodo(Todo todo);

    Todo getTodoById(Long id);

    List<Todo> elementsList(String todoUniqueKey);

    void changeCompleteStatus(Long id);

    void deleteTodo(Long id);
}