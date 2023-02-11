package com.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "text")
    private String text;

    @Column(name = "is_completed")
    private boolean isCompleted;
    @Column(name = "todo_unique_key")
    private String todoUniqueKey;

    public Todo() {}

    public Todo(String text) { this.text = text; }

    public String getTodoUniqueKey() {
        return todoUniqueKey;
    }

    public void setTodoUniqueKey(String todoUniqueKey) {
        this.todoUniqueKey = todoUniqueKey;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() { return isCompleted; }

    public void setCompleted(boolean completed) { isCompleted = completed; }

    public String getId() { return todoUniqueKey; }

    public void setId(String id) { this.todoUniqueKey = id; }

    @Override
    public String toString() {
        return todoUniqueKey + ") " + text + " isCompleted" + isCompleted + " id-" + todoUniqueKey;
    }
}