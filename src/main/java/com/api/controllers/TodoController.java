package com.api.controllers;

import com.api.model.Todo;
import com.api.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class TodoController {

    private final TodoService service;

    @Autowired
    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        return service.getAllTodos();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> saveTodo(@RequestBody Todo todo) {
        service.saveNewTodo(todo);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}