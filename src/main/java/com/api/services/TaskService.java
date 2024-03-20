package com.api.services;

import com.api.model.Task;

public interface TaskService {
    void saveNewTask(Task task);
    void updateTask(Task editedTask, Long id);
    void changeCompletedStatus(Long id);
    void deleteTask(Long id);
    void deleteAllTasks();
    void deleteCompletedTasks();
    Task getTaskById(Long id);
}