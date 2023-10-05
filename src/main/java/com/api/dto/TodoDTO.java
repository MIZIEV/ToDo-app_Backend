package com.api.dto;

import com.api.model.User;

import java.util.Objects;

public class TodoDTO {
    private String name;
    private String description;
    private boolean isCompleted;
    private String todoUniqueKey;
    private String username;
    private User todoOwner;

    public TodoDTO() {}

    public TodoDTO(String name, boolean isCompleted, String todoUniqueKey, String username, String description) {
        this.name = name;
        this.description = description;
        this.isCompleted = isCompleted;
        this.todoUniqueKey = todoUniqueKey;
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

    public String getTodoUniqueKey() {
        return todoUniqueKey;
    }

    public void setTodoUniqueKey(String todoUniqueKey) {
        this.todoUniqueKey = todoUniqueKey;
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
        TodoDTO todo = (TodoDTO) obj;
        return isCompleted == todo.isCompleted &&
                Objects.equals(name, todo.name) &&
                Objects.equals(description, todo.description) &&
                Objects.equals(username, todo.username) &&
                Objects.equals(todoUniqueKey, todo.todoUniqueKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, username, isCompleted, todoUniqueKey);
    }

    @Override
    public String toString() {
        return name + ", "+description+", " + isCompleted + ", " + todoUniqueKey;
    }
}