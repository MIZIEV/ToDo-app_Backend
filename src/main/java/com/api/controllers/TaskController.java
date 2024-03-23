package com.api.controllers;

import com.api.dto.TaskDTO;
import com.api.model.Task;
import com.api.model.User;
import com.api.services.TaskService;
import com.api.services.impl.TaskServiceImpl;
import com.api.services.UserService;
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
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public TaskController(TaskServiceImpl service, UserService userService) {
        this.taskService = service;
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveTask(@RequestBody TaskDTO taskDTO) {

        User user = userService.getUserByUsername(taskDTO.getUsername());
        Task newTask = convertToTask(taskDTO);
        newTask.setUser(user);

        taskService.saveNewTask(newTask);

        return new ResponseEntity<>(taskDTO,HttpStatus.CREATED);
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

        taskDTO.setName(task.getName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setUsername(task.getUser().getUsername());
        taskDTO.setCompleted(task.isCompleted());

        return taskDTO;
    }
}