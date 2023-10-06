package com.api.dto;

public class TodoElementDto {
    private String elementName;

    private boolean isCompleted;


    public TodoElementDto(){}

    public TodoElementDto(String elementName, boolean isCompleted) {
        this.elementName = elementName;
        this.isCompleted = isCompleted;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}