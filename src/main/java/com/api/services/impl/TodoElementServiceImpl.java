package com.api.services.impl;

import com.api.model.TodoElement;
import com.api.repositories.TodoElementRepository;
import com.api.services.TodoElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoElementServiceImpl implements TodoElementService {

    private final TodoElementRepository repository;

    @Autowired
    public TodoElementServiceImpl(TodoElementRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveElement(TodoElement todoElement) {

    }
}
