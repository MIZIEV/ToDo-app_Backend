package com.api.services;

import com.api.model.Task;

public interface TaskService {
    void saveNewTodo(Task task);
    void updateTodo(Task editedTask, String todoUniqueKey);
    void changeCompletedStatus(String todoUniqueKey);
    void deleteTodo(String key);
    void deleteAllTodos();
    void deleteCompletedTodo();
    Task getTodoByUniqueKey(String uniqueKey);
}