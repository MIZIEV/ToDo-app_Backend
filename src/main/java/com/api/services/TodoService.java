package com.api.services;

import com.api.model.Todo;
import com.api.repositories.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
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
        repository.save(todo);
    }

    @Transactional(readOnly = false)
    public void deleteTodo(String key) {
        Todo todoForDelete = getTodoByUniqueKey((key));
        repository.delete(todoForDelete);
    }

    @Transactional(readOnly = false)
    public void deleteAllTodos(){
        repository.deleteAll();
    }
    @Transactional(readOnly = false)
    public void deleteCompletedTodo(List<Todo> todoList){
        repository.deleteAll(todoList);
    }

    public Todo getTodoByUniqueKey(String uniqueKey) {
        return repository.findByTodoUniqueKey(uniqueKey);
    }
}