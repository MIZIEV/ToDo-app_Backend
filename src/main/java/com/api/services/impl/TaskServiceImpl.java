package com.api.services.impl;

import com.api.model.Task;
import com.api.repositories.TaskRepository;
import com.api.exception.EmptyFieldException;
import com.api.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void saveNewTodo(Task task) {
        if (task.getName() == null) {
            throw new EmptyFieldException("EmptyFieldException: Field \"text\" is empty ");
        } else {
            enrichTodo(task);
            todoRepository.save(task);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void updateTodo(Task editedTask, Long id) {

        Optional<Task> taskForUpdating = todoRepository.findById(id);

        if (taskForUpdating.isPresent()) {
            taskForUpdating.get().setName(editedTask.getName());
            taskForUpdating.get().setDescription(editedTask.getDescription());
            todoRepository.save(taskForUpdating.get());
        } else {
            throw null; // todo create exception!!!
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void changeCompletedStatus(String todoUniqueKey) {
        Task task = this.getTodoByUniqueKey(todoUniqueKey);

        task.setCompleted(!task.isCompleted());
        this.saveNewTodo(task);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteTodo(String key) {
        Task taskForDelete = getTodoByUniqueKey((key));
        todoRepository.delete(taskForDelete);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteAllTodos() {
        todoRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteCompletedTodo() {
        List<Task> completedTasks = todoRepository.findAllByIsCompleted(true);
        todoRepository.deleteAll(completedTasks);
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTodoByUniqueKey(String uniqueKey) {
        return null;
    }

    private void enrichTodo(Task task) {
        task.setCreatedAt(LocalDateTime.now());
    }
}