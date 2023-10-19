package com.api.services.impl;

import com.api.model.Todo;
import com.api.repositories.TodosRepository;
import com.api.exception.EmptyFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TodoServiceImpl implements TodoService{

    private final TodosRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodosRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public void saveNewTodo(Todo todo) {
        if (todo.getName() == null) {
            throw new EmptyFieldException("EmptyFieldException: Field \"text\" is empty ");
        } else {
            enrichTodo(todo);
            todoRepository.save(todo);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void updateTodo(Todo editedTodo, String todoUniqueKey) {

        Todo todoForUpdating = todoRepository.findByTodoUniqueKey(todoUniqueKey);
        todoForUpdating.setName(editedTodo.getName());
        todoForUpdating.setDescription(editedTodo.getDescription());
        todoRepository.save(todoForUpdating);
    }

    @Override
    @Transactional(readOnly = false)
    public void changeCompletedStatus(String todoUniqueKey) {
        Todo todo = this.getTodoByUniqueKey(todoUniqueKey);

        todo.setCompleted(!todo.isCompleted());
        this.saveNewTodo(todo);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteTodo(String key) {
        Todo todoForDelete = getTodoByUniqueKey((key));
        todoRepository.delete(todoForDelete);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteAllTodos() {
        todoRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteCompletedTodo() {
        List<Todo> completedTodos = todoRepository.findAllByIsCompleted(true);
        todoRepository.deleteAll(completedTodos);
    }
    @Override
    @Transactional(readOnly = true)
    public Todo getTodoByUniqueKey(String uniqueKey) {
        return todoRepository.findByTodoUniqueKey(uniqueKey);
    }

    private void enrichTodo(Todo todo) {
        todo.setCreatedAt(LocalDateTime.now());
    }
}