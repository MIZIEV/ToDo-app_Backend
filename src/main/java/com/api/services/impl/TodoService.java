package com.api.services.impl;

import com.api.model.Todo;

public interface TodoService {
    void saveNewTodo(Todo todo);
    void updateTodo(Todo editedTodo, String todoUniqueKey);
    void changeCompletedStatus(String todoUniqueKey);
    void deleteTodo(String key);
    void deleteAllTodos();
    void deleteCompletedTodo();
    Todo getTodoByUniqueKey(String uniqueKey);
}