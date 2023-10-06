package com.api.dto;

public class TodoElementDto {

    private Long id;
    private String elementName;
    private boolean isCompleted;

    public TodoElementDto() {}

    public TodoElementDto(Long id, String elementName, boolean isCompleted) {
        this.id = id;
        this.elementName = elementName;
        this.isCompleted = isCompleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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