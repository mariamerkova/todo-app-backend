package com.todoapp.service;
import com.todoapp.entity.model.TodoListDTO;

import java.util.List;
import java.util.Optional;

public interface TodoListService {

    List<TodoListDTO> findAll();

    void delete(Long id);

    TodoListDTO save(TodoListDTO todoListDTO);

    Optional<TodoListDTO> findById(Long id);

    TodoListDTO update(TodoListDTO todoListDTO);
}
