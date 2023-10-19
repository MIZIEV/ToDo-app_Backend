package com.api.services.impl;

import com.api.model.TodoElement;
import com.api.repositories.TodoElementRepository;
import com.api.repositories.TodosRepository;
import com.api.services.TodoElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TodoElementServiceImpl implements TodoElementService {

    private final TodoElementRepository elementRepository;
    private final TodosRepository todosRepository;

    @Autowired
    public TodoElementServiceImpl(TodoElementRepository elementRepository, TodosRepository todosRepository) {
        this.elementRepository = elementRepository;
        this.todosRepository = todosRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public void saveElement(TodoElement todoElement) {
        elementRepository.save(todoElement);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TodoElement> elementsList(String todoUniqueKey) {
        List<TodoElement> elements = todosRepository.findByTodoUniqueKey(todoUniqueKey).getElementsList();
        return elements;
    }

    @Override
    @Transactional(readOnly = false)
    public void changeCompleteStatus(Long id) {
        TodoElement todoElement = elementRepository.getReferenceById(id);

        if (todoElement.isCompleted()) {
            todoElement.setCompleted(false);
        } else {
            todoElement.setCompleted(true);
        }
        elementRepository.save(todoElement);
    }

    @Override
    public TodoElement getElementById(Long id) {
        TodoElement todoElement = elementRepository.getReferenceById(id);
        return todoElement;
    }
}