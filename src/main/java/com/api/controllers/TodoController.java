package com.api.controllers;

import com.api.dto.TodoDto;
import com.api.model.Task;
import com.api.model.Todo;
import com.api.services.TodoService;
import com.api.services.impl.TaskServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/task/{id}/todo")
public class TodoController {

    private final TodoService todoService;
    private final TaskServiceImpl todoServiceImpl;

    @Autowired
    public TodoController(TodoService todoService, TaskServiceImpl todoServiceImpl) {
        this.todoService = todoService;
        this.todoServiceImpl = todoServiceImpl;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> saveNewTodo(@PathVariable("id") Long id,
                                                  @RequestBody TodoDto todoDto) {

        Task ownerTask = todoServiceImpl.getTaskById(id);
        Todo todo = convertToTodoElement(todoDto);
        todo.setTodoOwner(ownerTask);

        todoService.saveElement(todo);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<TodoDto> getAllTodos(@PathVariable("id") Long id) {
        List<Todo> rawList = todoServiceImpl.getTaskById(id).getTodoList();
        List<TodoDto> readyList = new ArrayList<>();

        for (Todo todo : rawList) {
            readyList.add(convertToTodoElementDto(todo));
        }
        return readyList;
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<HttpStatus> changeCompleteStatus(@PathVariable("todoId") Long todoId) {
        todoService.changeCompleteStatus(todoId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<HttpStatus> deleteTodo(@PathVariable("todoId") Long todoId){

        todoService.deleteElement(todoId);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Todo convertToTodoElement(TodoDto todoDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(todoDto, Todo.class);
    }

    private TodoDto convertToTodoElementDto(Todo todo) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(todo, TodoDto.class);
    }
}