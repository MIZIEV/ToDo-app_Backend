package com.api.dto;

import java.util.Objects;

public class TodoElementDto {

    private Long id;
    private String elementName;
    private boolean isCompleted;

    public TodoElementDto() {
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(id, elementName, isCompleted);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TodoElementDto myClass = (TodoElementDto) obj;
        return Objects.equals(id, myClass.id) && Objects.equals(elementName, myClass.elementName) && isCompleted == myClass.isCompleted;
    }

    @Override
    public String toString() {
        return id + ")" + elementName + ", " + isCompleted;
    }
}