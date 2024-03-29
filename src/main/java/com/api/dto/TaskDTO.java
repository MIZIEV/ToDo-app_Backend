package com.api.dto;

import com.api.model.User;

import java.util.Objects;

public class TaskDTO {
    private String name;
    private String description;
    private boolean isCompleted;
    private String taskUniqueKey;
    private String username;
    private User todoOwner;

    public TaskDTO() {}

    public TaskDTO(String name, boolean isCompleted, String taskUniqueKey, String username, String description) {
        this.name = name;
        this.description = description;
        this.isCompleted = isCompleted;
        this.taskUniqueKey = taskUniqueKey;
        this.username = username;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getTaskUniqueKey() {
        return taskUniqueKey;
    }

    public void setTaskUniqueKey(String taskUniqueKey) {
        this.taskUniqueKey = taskUniqueKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getTodoOwner() {
        return todoOwner;
    }

    public void setTodoOwner(User todoOwner) {
        this.todoOwner = todoOwner;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TaskDTO todo = (TaskDTO) obj;
        return isCompleted == todo.isCompleted &&
                Objects.equals(name, todo.name) &&
                Objects.equals(description, todo.description) &&
                Objects.equals(username, todo.username) &&
                Objects.equals(taskUniqueKey, todo.taskUniqueKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, username, isCompleted, taskUniqueKey);
    }

    @Override
    public String toString() {
        return name + ", "+description+", " + isCompleted + ", " + taskUniqueKey;
    }
}