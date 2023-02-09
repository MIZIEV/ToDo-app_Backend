package com.app.controllers;

import com.app.model.Todo;
import com.app.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService service;

    @Autowired
    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping("/alltodos")
    public List<Todo> getAllTodos() {
        return service.getAllTodos();
    }

    @PostMapping
    public ResponseEntity<HttpStatus> saveTodo(@RequestBody Todo todo) {
        service.saveNewTodo(todo);
        return ResponseEntity.ok(HttpStatus.OK);

    }
}