package com.api.dto;

import java.util.Objects;

public class TodoDto {
    private Long id;
    private String todoName;
    private boolean isCompleted;

    public TodoDto() {}

    public TodoDto(Long id, String todoName, boolean isCompleted) {
        this.id = id;
        this.todoName = todoName;
        this.isCompleted = isCompleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTodoName() {
        return todoName;
    }

    public void setTodoName(String todoName) {
        this.todoName = todoName;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, todoName, isCompleted);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TodoDto myClass = (TodoDto) obj;
        return Objects.equals(id, myClass.id) && Objects.equals(todoName, myClass.todoName) && isCompleted == myClass.isCompleted;
    }

    @Override
    public String toString() {
        return id + ")" + todoName + ", " + isCompleted;
    }
}