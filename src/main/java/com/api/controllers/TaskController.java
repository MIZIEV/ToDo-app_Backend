package com.api.controllers;

import com.api.dto.TaskDTO;
import com.api.model.Task;
import com.api.model.User;
import com.api.services.TaskService;
import com.api.services.impl.TaskServiceImpl;
import com.api.services.UserService;
import com.api.exception.EmptyFieldException;
import com.api.exception.ErrorResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public TaskController(TaskServiceImpl service, UserService userService) {
        this.taskService = service;
        this.userService = userService;
    }

    @GetMapping("/tasks/{username}")
    public List<TaskDTO> getAllInCompletedTodos(@PathVariable String username) {
        User user = userService.getUserByUsername(username);

        List<Task> rawList = user.getTodoList();
        ArrayList<TaskDTO> readyList = new ArrayList<>();

        if (rawList != null) {
            for (Task task : rawList) {
                readyList.add(convertToTodoDTO(task));
            }
        } else {
            return null;
        }
        return readyList.stream().filter(taskDTO -> !taskDTO.isCompleted()).toList();
    }

    @GetMapping("/tasks-completed/{username}")
    public List<TaskDTO> getAllCompletedTodo(@PathVariable String username) {
        User user = userService.getUserByUsername(username);

        List<Task> rawList = user.getTodoList();
        ArrayList<TaskDTO> readyList = new ArrayList<>();

        if (rawList != null) {
            for (Task task : rawList) {
                readyList.add(convertToTodoDTO(task));
            }
        } else {
            return null;
        }
        return readyList.stream().filter(TaskDTO::isCompleted).toList();
    }

    @GetMapping("/task/{taskUniqueKey}")
    public TaskDTO getTodoByUniqueKey(@PathVariable String taskUniqueKey) {
        TaskDTO taskDTO = convertToTodoDTO(taskService.getTodoByUniqueKey(taskUniqueKey));

        return taskDTO;
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<?> updateTodo(@RequestBody TaskDTO updatedTodo,
                                        @PathVariable("id") Long id) {
        taskService.updateTodo(convertToTodo(updatedTodo), id);

        return new ResponseEntity<>("Task with id - " + id + " was updated.", HttpStatus.OK);
    }

    @PatchMapping("/task/complete/{taskUniqueKey}")
    public ResponseEntity<HttpStatus> changeCompletedStatus(@PathVariable String taskUniqueKey) {
        taskService.changeCompletedStatus(taskUniqueKey);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> saveTodo(@RequestBody TaskDTO taskDTO) {

        User user = userService.getUserByUsername(taskDTO.getUsername());
        Task newTask = convertToTodo(taskDTO);
        newTask.setUser(user);

        taskService.saveNewTodo(newTask);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/task/{taskUniqueKey}")
    public ResponseEntity<HttpStatus> deleteTodo(@PathVariable String taskUniqueKey) {
        taskService.deleteTodo(taskUniqueKey);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/tasks/delete")
    public ResponseEntity<HttpStatus> deleteAllTodos() {
        taskService.deleteAllTodos();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/tasks/delete_completed")
    public ResponseEntity<HttpStatus> deleteCompletedTodo() {
        taskService.deleteCompletedTodo();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Task convertToTodo(TaskDTO taskDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(taskDTO, Task.class);
    }

    private TaskDTO convertToTodoDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setName(task.getName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setUsername(task.getUser().getUsername());
        taskDTO.setCompleted(task.isCompleted());

        return taskDTO;
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(EmptyFieldException exception) {
        ErrorResponse response = new ErrorResponse("Text field mustn't be empty!", LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}