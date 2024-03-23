package com.api.dto;

import com.api.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class TaskDTO {
    @NotNull(message = "The field `name` mustn't be empty!")
    @Size(min = 4, message = "The field `name` must be longer than 4 characters!")
    private String name;
    @NotNull(message = "The field `description` mustn't be empty!")
    @Size(min = 5, message = "The field `description` must be longer than 5 characters!")
    private String description;
    private boolean isCompleted;
    @NotNull(message = "The field `username` mustn't be empty!")
    private String username;
    private User todoOwner;

    public TaskDTO() {}

    public TaskDTO(String name, boolean isCompleted, String username, String description) {
        this.name = name;
        this.description = description;
        this.isCompleted = isCompleted;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    @JsonProperty("isCompleted")
    public void setCompleted(boolean completed) {
        isCompleted = completed;
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
                Objects.equals(username, todo.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, username, isCompleted);
    }

    @Override
    public String toString() {
        return name + ", " + description + ", " + isCompleted;
    }
}