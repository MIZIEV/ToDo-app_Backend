package com.api.services;

import com.api.model.Task;

import java.util.List;

public interface TaskService {
    void saveNewTask(Task task);

    List<Task> getCompletedTasks(String username);

    List<Task> getInCompletedTasks(String username);

    void updateTask(Task editedTask, Long id);

    void changeCompletedStatus(Long id);

    void deleteTask(Long id);

    void deleteAllTasks();

    void deleteCompletedTasks();

    Task getTaskById(Long id);
}