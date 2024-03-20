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
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;
    private final TaskServiceImpl todoServiceImpl;

    @Autowired
    public TodoController(TodoService todoService, TaskServiceImpl todoServiceImpl) {
        this.todoService = todoService;
        this.todoServiceImpl = todoServiceImpl;
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<HttpStatus> saveNewElement(@PathVariable("id") Long id,
                                                     @RequestBody TodoDto todoDto) {

        Task ownerTask = todoServiceImpl.getTaskById(id);
        Todo todo = convertToTodoElement(todoDto);
        todo.setTodoOwner(ownerTask);

        todoService.saveElement(todo);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public List<TodoDto> getAllElements(@PathVariable("id") Long id) {
        List<Todo> rawList = todoServiceImpl.getTaskById(id).getTodoList();
        List<TodoDto> readyList = new ArrayList<>();

        for (Todo todo : rawList) {
            readyList.add(convertToTodoElementDto(todo));
        }
        return readyList;
    }

    @PatchMapping("/change-status/{id}")
    public ResponseEntity<HttpStatus> changeCompleteStatus(@PathVariable Long id) {
        todoService.changeCompleteStatus(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<HttpStatus> deleteElement(@PathVariable Long id){

        todoService.deleteElement(id);

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