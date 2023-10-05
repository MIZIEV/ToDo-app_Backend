package com.api.dto;

public class TodoElementDto {
    private String elementName;

    private boolean isCompleted;

    private String todoOwnerName;

    public TodoElementDto(){}

    public TodoElementDto(String elementName, boolean isCompleted,String todoOwnerName) {
        this.elementName = elementName;
        this.todoOwnerName=todoOwnerName;
        this.isCompleted = isCompleted;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getTodoOwnerName() {
        return todoOwnerName;
    }

    public void setTodoOwnerName(String todoOwnerName) {
        this.todoOwnerName = todoOwnerName;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}