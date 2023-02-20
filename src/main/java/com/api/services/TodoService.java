package com.api.services;

import com.api.model.Todo;
import com.api.repositories.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TodoService {

    private final TodosRepository repository;

    @Autowired
    public TodoService(TodosRepository repository) {
        this.repository = repository;
    }

    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    @Transactional(readOnly = false)
    public void saveNewTodo(Todo todo) {
        enrichTodo(todo);
        repository.save(todo);
    }

    @Transactional(readOnly = false)
    public void changeCompletedStatus(Todo editedTodo, String todoUniqueKey) {
        Todo oldTodo = this.getTodoByUniqueKey(todoUniqueKey);
        oldTodo.setCompleted(editedTodo.isCompleted());
        this.saveNewTodo(oldTodo);
    }

    @Transactional(readOnly = false)
    public void deleteTodo(String key) {
        Todo todoForDelete = getTodoByUniqueKey((key));
        repository.delete(todoForDelete);
    }

    @Transactional(readOnly = false)
    public void deleteAllTodos() {
        repository.deleteAll();
    }

    @Transactional(readOnly = false)
    public void deleteCompletedTodo() {
        List<Todo> completedTodos = repository.findAllByIsCompleted(true);
        repository.deleteAll(completedTodos);
    }

    public Todo getTodoByUniqueKey(String uniqueKey) {
        return repository.findByTodoUniqueKey(uniqueKey);
    }

    private void enrichTodo(Todo todo) {
        todo.setCreatedAt(LocalDateTime.now());
    }
}