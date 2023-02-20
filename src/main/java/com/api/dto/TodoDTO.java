package com.api.dto;

import java.util.Objects;

public class TodoDTO {
    private String text;
    private boolean isCompleted;
    private String todoUniqueKey;

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