package com.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "text")
    //@NotEmpty(message = "Text field mustn't be empty.")
    private String text;
    @Column(name = "description")
    private String description;
    @Column(name = "is_completed")
    private boolean isCompleted;
    @Column(name = "todo_unique_key")
    //@NotEmpty(message = "Key field mustn't be empty.")
    private String todoUniqueKey;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "todoOwner")
    @JsonBackReference
    private List<TodoElement> elementsList;

    public Todo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TodoElement> getElementsList() {
        return elementsList;
    }

    public void setElementsList(List<TodoElement> elementsList) {
        this.elementsList = elementsList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, description, isCompleted, todoUniqueKey, createdAt);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Todo todo = (Todo) obj;
        return id == todo.id &&
                isCompleted == todo.isCompleted &&
                Objects.equals(text, todo.text) &&
                Objects.equals(description, todo.description) &&
                Objects.equals(todoUniqueKey, todo.todoUniqueKey) &&
                Objects.equals(createdAt, todo.createdAt);
    }

    @Override
    public String toString() {
        return id + ") " + text + ", " + description + ", " + isCompleted + ", " + todoUniqueKey + ", " + createdAt;
    }
}