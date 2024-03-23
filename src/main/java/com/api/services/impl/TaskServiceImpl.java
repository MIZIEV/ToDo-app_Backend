package com.api.services.impl;

import com.api.model.Task;
import com.api.repositories.TaskRepository;
import com.api.util.exception.EmptyFieldException;
import com.api.services.TaskService;
import com.api.util.exception.TaskManagerApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

    private final TaskRepository todoRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public void saveNewTask(Task task) {
        if (task.getName() == null) {
            throw new EmptyFieldException("EmptyFieldException: Field \"text\" is empty ");
        } else {
            enrichTodo(task);
            todoRepository.save(task);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void updateTask(Task editedTask, Long id) {

        Optional<Task> taskForUpdating = todoRepository.findById(id);

        if (taskForUpdating.isPresent()) {
            taskForUpdating.get().setName(editedTask.getName());
            taskForUpdating.get().setDescription(editedTask.getDescription());
            todoRepository.save(taskForUpdating.get());
        } else {
            throw new TaskManagerApiException("Task with id - " + id + " not found!", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void changeCompletedStatus(Long id) {
        Task task = this.getTaskById(id);

        task.setCompleted(!task.isCompleted());
        this.saveNewTask(task);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteTask(Long id) {
        Task taskForDelete = getTaskById(id);
        todoRepository.delete(taskForDelete);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteAllTasks() {
        todoRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteCompletedTasks() {
        List<Task> completedTasks = todoRepository.findAllByIsCompleted(true);
        todoRepository.deleteAll(completedTasks);
    }

    @Override
    public Task getTaskById(Long id) {
        Optional<Task> task = todoRepository.findById(id);

        if (task.isPresent()) {
            return task.get();
        } else {
            throw new TaskManagerApiException("Task with id - " + id + " not found!", HttpStatus.NOT_FOUND);
        }
    }

    private void enrichTodo(Task task) {
        task.setCreatedAt(LocalDateTime.now());
    }
}