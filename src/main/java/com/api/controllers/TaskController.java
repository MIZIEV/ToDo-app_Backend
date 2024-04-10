package com.api.controllers;

import com.api.dto.TaskDTO;
import com.api.model.Task;
import com.api.model.User;
import com.api.services.TaskService;
import com.api.services.impl.TaskServiceImpl;
import com.api.services.UserService;
import com.api.util.exception.TaskManagerApiException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public TaskController(TaskServiceImpl service, UserService userService) {
        this.taskService = service;
        this.userService = userService;
    }

    @PostMapping("/add/{username}")
    public ResponseEntity<?> saveTask(@RequestParam("username") String username,
            @Valid @RequestBody TaskDTO taskDTO, BindingResult result) {

        if (result.hasErrors()) {

            StringBuffer errorMessage = new StringBuffer("Validation exception: ");

            for (FieldError fieldError : result.getFieldErrors()) {
                errorMessage.append(fieldError.getField()).append(": ")
                        .append(fieldError.getDefaultMessage()).append("; ");
            }

            throw new TaskManagerApiException(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        } else {
            User user = userService.getUserByUsername(username);
            Task newTask = convertToTask(taskDTO);
            newTask.setUser(user);

            taskService.saveNewTask(newTask);

            return new ResponseEntity<>(taskDTO, HttpStatus.CREATED);
        }
    }

    @GetMapping("/list/{username}")
    public ResponseEntity<?> getAllInCompletedTask(@PathVariable String username) {
        User user = userService.getUserByUsername(username);

        List<Task> rawList = user.getTodoList();
        ArrayList<TaskDTO> readyList = new ArrayList<>();

        if (rawList != null) {
            for (Task task : rawList) {
                readyList.add(convertToTaskDTO(task));
            }
        } else {
            return null;
        }

        return new ResponseEntity<>(readyList.stream().filter(taskDTO -> !taskDTO.isCompleted()).toList(), HttpStatus.OK);
    }

    @GetMapping("/list-completed/{username}")
    public ResponseEntity<?> getAllCompletedTask(@PathVariable String username) {
        User user = userService.getUserByUsername(username);

        List<Task> rawList = user.getTodoList();
        ArrayList<TaskDTO> readyList = new ArrayList<>();

        if (rawList != null) {
            for (Task task : rawList) {
                readyList.add(convertToTaskDTO(task));
            }
        } else {
            return null;
        }
        return new ResponseEntity<>(readyList.stream().filter(TaskDTO::isCompleted).toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable("id") Long id) {
        TaskDTO taskDTO = convertToTaskDTO(taskService.getTaskById(id));

        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@RequestBody TaskDTO updatedTodo,
                                        @PathVariable("id") Long id) {
        taskService.updateTask(convertToTask(updatedTodo), id);

        return new ResponseEntity<>("Task with id - " + id + " was updated.", HttpStatus.OK);
    }

    @PatchMapping("/complete/{id}")
    public ResponseEntity<HttpStatus> changeCompletedStatus(@PathVariable("id") Long id) {
        taskService.changeCompletedStatus(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>("Task with id - " + id + " was deleted successfully!", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tasks/delete")
    public ResponseEntity<HttpStatus> deleteAllTasks() {
        taskService.deleteAllTasks();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/tasks/delete_completed")
    public ResponseEntity<HttpStatus> deleteCompletedTasks() {
        taskService.deleteCompletedTasks();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Task convertToTask(TaskDTO taskDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(taskDTO, Task.class);
    }

    private TaskDTO convertToTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId(task.getId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setCompleted(task.isCompleted());
        taskDTO.setName(task.getName());
        taskDTO.setUserId(task.getUser().getId());

        return taskDTO;
    }
}