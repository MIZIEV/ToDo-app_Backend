package com.api.services.impl;

import com.api.model.Todo;
import com.api.repositories.TodoRepository;
import com.api.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TodoServiceImpl implements TodoService {

    private final TodoRepository elementRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository elementRepository) {
        this.elementRepository = elementRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public void saveTodo(Todo todo) {
        elementRepository.save(todo);
    }

    @Override
    @Transactional(readOnly = false)
    public void changeCompleteStatus(Long id) {
        Todo todo = elementRepository.getReferenceById(id);

        if (todo.isCompleted()) {
            todo.setCompleted(false);
        } else {
            todo.setCompleted(true);
        }
        elementRepository.save(todo);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteTodo(Long id) {
        elementRepository.delete(getTodoById(id));
    }

    @Override
    public Todo getTodoById(Long id) {
        Todo todo = elementRepository.getReferenceById(id);
        return todo;
    }
}