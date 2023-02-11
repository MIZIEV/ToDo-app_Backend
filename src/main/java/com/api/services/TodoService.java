package com.api.services;

import com.api.model.Todo;
import com.api.repositories.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TodoService {

    private final TodosRepository repository;

    @Autowired
    public TodoService(TodosRepository repository) {
        this.repository = repository;
    }

    public List<Todo> getAllTodos(){
        return  repository.findAll();
    }

    @Transactional(readOnly = false)
    public void saveNewTodo(Todo todo){
        repository.save(todo);
    }
}