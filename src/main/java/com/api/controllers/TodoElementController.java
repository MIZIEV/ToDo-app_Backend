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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
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

    private TodoElement convertToTodoElement(TodoElementDto todoElementDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(todoElementDto, TodoElement.class);
    }
}
