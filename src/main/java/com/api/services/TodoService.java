package com.api.services;

import com.api.model.Todo;
import com.api.repositories.TodosRepository;
import com.api.util.EmptyFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TodoService {

    private final TodosRepository todoRepository;

    @Autowired
    public TodoService(TodosRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Transactional(readOnly = false)
    public void saveNewTodo(Todo todo) {
        if (todo.getText() == null) {
            throw new EmptyFieldException("EmptyFieldException: Field \"text\" is empty ");
        } else {
            enrichTodo(todo);
            todoRepository.save(todo);
        }
    }

    @Transactional(readOnly = false)
    public void updateTodo(Todo editedTodo, String todoUniqueKey) {

        Todo todoForUpdating = todoRepository.findByTodoUniqueKey(todoUniqueKey);
        todoForUpdating.setText(editedTodo.getText());
        todoRepository.save(todoForUpdating);
    }

    @Transactional(readOnly = false)
    public void changeCompletedStatus(String todoUniqueKey) {
        Todo todo = this.getTodoByUniqueKey(todoUniqueKey);

        if (todo.isCompleted()){
            todo.setCompleted(false);
        } else{
            todo.setCompleted(true);
        }
        this.saveNewTodo(todo);
    }

    @Transactional(readOnly = false)
    public void deleteTodo(String key) {
        Todo todoForDelete = getTodoByUniqueKey((key));
        todoRepository.delete(todoForDelete);
    }

    @Transactional(readOnly = false)
    public void deleteAllTodos() {
        todoRepository.deleteAll();
    }

    @Transactional(readOnly = false)
    public void deleteCompletedTodo() {
        List<Todo> completedTodos = todoRepository.findAllByIsCompleted(true);
        todoRepository.deleteAll(completedTodos);
    }

    public Todo getTodoByUniqueKey(String uniqueKey) {
        return todoRepository.findByTodoUniqueKey(uniqueKey);
    }

    private void enrichTodo(Todo todo) {
        todo.setCreatedAt(LocalDateTime.now());
    }
}