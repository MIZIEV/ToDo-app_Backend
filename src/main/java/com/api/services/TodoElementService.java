package com.api.services;

import com.api.model.TodoElement;

import java.util.List;

public interface TodoElementService {

    void saveElement(TodoElement todoElement);

    TodoElement getElementById(Long id);

    List<TodoElement> elementsList(String todoUniqueKey);

    void changeCompleteStatus(Long id);
}