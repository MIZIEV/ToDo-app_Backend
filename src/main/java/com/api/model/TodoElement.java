package com.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "todo_element")
public class TodoElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "element_name")
    private String elementName;

    @Column(name = "is_completed")
    private boolean isCompleted;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "element_id", referencedColumnName = "id")
    private Todo todoOwner;

    public TodoElement() {
    }

    public TodoElement(String elementName, Todo todoOwner, boolean isCompleted) {
        this.elementName = elementName;
        this.isCompleted = isCompleted;
        this.todoOwner = todoOwner;
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

    public Todo getTodoOwner() {
        return todoOwner;
    }

    public void setTodoOwner(Todo todoOwner) {
        this.todoOwner = todoOwner;
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
        TodoElement todoElement = (TodoElement) obj;
        return Objects.equals(id, todoElement.id) &&
                Objects.equals(elementName, todoElement.elementName) &&
                isCompleted == todoElement.isCompleted;
    }

    @Override
    public String toString() {
        return id + ") " + elementName + ", is completed - " + isCompleted;
    }
}