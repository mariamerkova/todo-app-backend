package com.todoapp.service;
import com.todoapp.entity.User;
import com.todoapp.entity.model.TodoListDTO;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface TodoListService {

    List<TodoListDTO> findAll(String name);

    void delete(Long id);

    TodoListDTO save(String username, TodoListDTO todoListDTO) throws NotFoundException;

    Optional<TodoListDTO> findById(Long id);

    TodoListDTO update(TodoListDTO todoListDTO);
}
