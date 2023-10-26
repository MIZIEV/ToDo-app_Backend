package com.api.services;

import com.api.model.Todo;

import java.util.List;

public interface TodoService {

    void saveElement(Todo todo);

    Todo getElementById(Long id);

    List<Todo> elementsList(String todoUniqueKey);

    void changeCompleteStatus(Long id);

    void deleteElement(Long id);
}