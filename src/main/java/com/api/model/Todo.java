package com.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "element_name")
    private String todoName;

    @Column(name = "is_completed")
    private boolean isCompleted;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "todo_id", referencedColumnName = "id")
    private Task taskOwner;

    public Todo() {
    }

    public Todo(String todoName, Task taskOwner, boolean isCompleted) {
        this.todoName = todoName;
        this.isCompleted = isCompleted;
        this.taskOwner = taskOwner;
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

    public void setTodoName(String elementName) {
        this.todoName = elementName;
    }

    public Task getTodoOwner() {
        return taskOwner;
    }

    public void setTodoOwner(Task taskOwner) {
        this.taskOwner = taskOwner;
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
        Todo todo = (Todo) obj;
        return Objects.equals(id, todo.id) &&
                Objects.equals(todoName, todo.todoName) &&
                isCompleted == todo.isCompleted;
    }

    @Override
    public String toString() {
        return id + ") " + todoName + ", is completed - " + isCompleted;
    }
}