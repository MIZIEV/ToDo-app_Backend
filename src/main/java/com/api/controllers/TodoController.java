package com.api.controllers;

import com.api.dto.TodoDTO;
import com.api.model.Todo;
import com.api.model.User;
import com.api.services.TodoService;
import com.api.services.UserService;
import com.api.util.EmptyFieldException;
import com.api.util.TodoErrorResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TodoController {

    private final TodoService todoService;
    private final UserService userService;

    @Autowired
    public TodoController(TodoService service, UserService userService) {
        this.todoService = service;
        this.userService = userService;
    }

    @GetMapping("/todos/{username}")
    public List<TodoDTO> getAllTodos(@PathVariable String username) {
        User user = userService.getUserByUsername(username);

        List<Todo> rawList = user.getTodoList();
        ArrayList<TodoDTO> readyList = new ArrayList<>();

        for (int i = 0; i < rawList.size(); i++) {

            readyList.add(convertToTodoDTO(rawList.get(i)));
        }

        return readyList.stream().filter(todoDTO -> !todoDTO.isCompleted()).toList();
        //return service.getAllTodos().stream().map(this::convertToTodoDTO).collect(Collectors.toList());
    }

    @GetMapping("/todos-completed/{username}")
    public List<TodoDTO> getAllCompletedTodo(@PathVariable String username){
        User user = userService.getUserByUsername(username);

        List<Todo> rawList = user.getTodoList();
        ArrayList<TodoDTO> readyList = new ArrayList<>();

        for (int i = 0; i < rawList.size(); i++) {

            readyList.add(convertToTodoDTO(rawList.get(i)));
        }

        return readyList.stream().filter(TodoDTO::isCompleted).toList();
    }

    @GetMapping("/todo/{todoUniqueKey}")
    public TodoDTO getTodoByUniqueKey(@PathVariable String todoUniqueKey) {
        TodoDTO todoDTO = convertToTodoDTO(todoService.getTodoByUniqueKey(todoUniqueKey));

        return todoDTO;
    }

    @PutMapping("/todo/{todoUniqueKey}")
    public ResponseEntity<HttpStatus> updateTodo(@RequestBody TodoDTO updatedTodo,
                                                 @PathVariable String todoUniqueKey) {
        todoService.updateTodo(convertToTodo(updatedTodo), todoUniqueKey);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/todo/complete/{todoUniqueKey}")
    public ResponseEntity<HttpStatus> changeCompletedStatus(@PathVariable String todoUniqueKey) {
        todoService.changeCompletedStatus(todoUniqueKey);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> saveTodo(@RequestBody TodoDTO todoDTO) {

        User user = userService.getUserByUsername(todoDTO.getUsername());
        Todo newTodo = convertToTodo(todoDTO);
        newTodo.setUser(user);

        todoService.saveNewTodo(newTodo);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/todo/{todoUniqueKey}")
    public ResponseEntity<HttpStatus> deleteTodo(@PathVariable String todoUniqueKey) {
        todoService.deleteTodo(todoUniqueKey);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/todos/delete")
    public ResponseEntity<HttpStatus> deleteAllTodos() {
        todoService.deleteAllTodos();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/todos/delete_completed")
    public ResponseEntity<HttpStatus> deleteCompletedTodo() {
        todoService.deleteCompletedTodo();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Todo convertToTodo(TodoDTO todoDTO) {
 /*       Todo todo = new Todo();

        todo.setText(todoDTO.getText());
        todo.setCompleted(todoDTO.isCompleted());
        todo.setUser(todoDTO.getTodoOwner());
        todo.setTodoUniqueKey(todoDTO.getTodoUniqueKey());
*/
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(todoDTO, Todo.class);
        //return todo;
    }

    private TodoDTO convertToTodoDTO(Todo todo) {
        TodoDTO todoDTO = new TodoDTO();

        todoDTO.setName(todo.getName());
        todoDTO.setDescription(todo.getDescription());
        todoDTO.setUsername(todo.getUser().getUsername());
        todoDTO.setCompleted(todo.isCompleted());
        todoDTO.setTodoUniqueKey(todo.getTodoUniqueKey());
        //todoDTO.setTodoOwner(todo.getUser());

        return todoDTO;
    }

    @ExceptionHandler
    private ResponseEntity<TodoErrorResponse> handleException(EmptyFieldException exception) {
        TodoErrorResponse response = new TodoErrorResponse("Text field mustn't be empty!", LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}