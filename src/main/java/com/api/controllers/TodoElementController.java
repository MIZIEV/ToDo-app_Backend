package com.api.controllers;

import com.api.dto.TodoElementDto;
import com.api.model.Todo;
import com.api.model.TodoElement;
import com.api.services.TodoElementService;
import com.api.services.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/element")
public class TodoElementController {

    private final TodoElementService todoElementService;
    private final TodoService todoService;

    @Autowired
    public TodoElementController(TodoElementService todoElementService, TodoService todoService) {
        this.todoElementService = todoElementService;
        this.todoService = todoService;
    }

    @PostMapping("/add/{todoUniqueKey}")
    public ResponseEntity<HttpStatus> saveNewElement(@PathVariable String todoUniqueKey,
                                                     @RequestBody TodoElementDto todoElementDto) {

        Todo ownerTodo = todoService.getTodoByUniqueKey(todoUniqueKey);
        TodoElement todoElement = convertToTodoElement(todoElementDto);
        todoElement.setTodoOwner(ownerTodo);

        todoElementService.saveElement(todoElement);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list/{todoUniqueKey}")
    public List<TodoElementDto> getAllElements(@PathVariable String todoUniqueKey) {
        List<TodoElement> rawList = todoService.getTodoByUniqueKey(todoUniqueKey).getElementsList();
        List<TodoElementDto> readyList = new ArrayList<>();

        for (TodoElement todoElement : rawList) {
            readyList.add(convertToTodoElementDto(todoElement));
        }
        return readyList;
    }

    @PatchMapping("/change-status/{id}")
    public ResponseEntity<HttpStatus> changeCompleteStatus(@PathVariable Long id) {
        todoElementService.changeCompleteStatus(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private TodoElement convertToTodoElement(TodoElementDto todoElementDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(todoElementDto, TodoElement.class);
    }

    private TodoElementDto convertToTodoElementDto(TodoElement todoElement) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(todoElement, TodoElementDto.class);
    }
}