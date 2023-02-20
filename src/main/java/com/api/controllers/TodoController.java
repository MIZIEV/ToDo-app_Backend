package com.api.controllers;

import com.api.dto.TodoDTO;
import com.api.model.Todo;
import com.api.services.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<TodoDTO> getAllTodos() {
        return service.getAllTodos().stream().map(this::convertToTodoDTO).collect(Collectors.toList());
    }

    @PutMapping("/todo/{todoUniqueKey}")
    public ResponseEntity<HttpStatus> updateTodo(@RequestBody Todo editedTodo, @PathVariable String todoUniqueKey) {
        service.changeCompletedStatus(editedTodo, todoUniqueKey);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> saveTodo(@RequestBody TodoDTO todoDTO) {
        service.saveNewTodo(convertToTodo(todoDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/todo/{todoUniqueKey}")
    public ResponseEntity<HttpStatus> deleteTodo(@PathVariable String todoUniqueKey) {
        service.deleteTodo(todoUniqueKey);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/todos/delete")
    public ResponseEntity<HttpStatus> deleteAllTodos() {
        service.deleteAllTodos();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/todos/delete_completed")
    public ResponseEntity<HttpStatus> deleteCompletedTodo() {
        service.deleteCompletedTodo();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Todo convertToTodo(TodoDTO todoDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(todoDTO, Todo.class);
    }

    private TodoDTO convertToTodoDTO(Todo todo) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(todo, TodoDTO.class);
    }
}