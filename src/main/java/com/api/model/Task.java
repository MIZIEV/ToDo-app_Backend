package com.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    //@NotEmpty(message = "Text field mustn't be empty.")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "is_completed")
    private boolean isCompleted;
    @Column(name = "task_unique_key")
    //@NotEmpty(message = "Key field mustn't be empty.")
    private String taskUniqueKey;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "taskOwner")
    @JsonManagedReference
    private List<Todo> elementsList;

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String text) {
        this.name = text;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getTaskUniqueKey() {
        return taskUniqueKey;
    }

    public void setTaskUniqueKey(String todoUniqueKey) {
        this.taskUniqueKey = todoUniqueKey;
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

    public List<Todo> getElementsList() {
        return elementsList;
    }

    public void setElementsList(List<Todo> elementsList) {
        this.elementsList = elementsList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, isCompleted, taskUniqueKey, createdAt);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return id == task.id &&
                isCompleted == task.isCompleted &&
                Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                Objects.equals(taskUniqueKey, task.taskUniqueKey) &&
                Objects.equals(createdAt, task.createdAt);
    }

    @Override
    public String toString() {
        return id + ") " + name + ", " + description + ", " + isCompleted + ", " + taskUniqueKey + ", " + createdAt;
    }
}