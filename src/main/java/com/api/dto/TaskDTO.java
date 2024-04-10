package com.api.dto;

import com.api.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class TaskDTO {
    private int id;
    @NotNull(message = "The field `name` mustn't be empty!")
    @Size(min = 4, message = "The field `name` must be longer than 4 characters!")
    private String name;
    @NotNull(message = "The field `description` mustn't be empty!")
    @Size(min = 5, message = "The field `description` must be longer than 5 characters!")
    private String description;

    private boolean isCompleted;

    private long userId;


    public TaskDTO() {
    }

    public TaskDTO(int id, String name, boolean isCompleted, String description, long userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isCompleted = isCompleted;
        this.userId = userId;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    @JsonProperty("isCompleted")
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public long getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TaskDTO taskDto = (TaskDTO) obj;
        return isCompleted == taskDto.isCompleted &&
                Objects.equals(id, taskDto.id) &&
                Objects.equals(name, taskDto.name) &&
                Objects.equals(description, taskDto.description) &&
                Objects.equals(userId, taskDto.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, isCompleted, userId);
    }

    @Override
    public String toString() {
        return id + ") " + name + ", " + description + ", " + isCompleted;
    }
}