package com.api.dto;

import com.api.model.User;

import java.util.Objects;

public class TodoDTO {
    private String text;
    private boolean isCompleted;
    private String todoUniqueKey;
    private String username;
    private User todoOwner;
    public TodoDTO(String text, boolean isCompleted, String todoUniqueKey, String username) {
        this.text = text;
        this.isCompleted = isCompleted;
        this.todoUniqueKey = todoUniqueKey;
        this.username = username;
    }

    public String getText() { return text; }

    public void setText(String text) {
        this.text = text;
    }

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
                Objects.equals(text, todo.text) &&
                Objects.equals(todoUniqueKey, todo.todoUniqueKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, isCompleted, todoUniqueKey);
    }

    @Override
    public String toString() {
        return text + ", " + isCompleted + ", " + todoUniqueKey;
    }
}