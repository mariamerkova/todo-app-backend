package com.todoapp.service;

import com.todoapp.entity.TodoList;
import com.todoapp.entity.model.TodoListDTO;
import com.todoapp.repository.TodoListRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TodoListServiceImpl implements TodoListService{

    private TodoListRepository todoListRepository;

    public List<TodoListDTO> findAll() {
        return todoListRepository.findAll()
                .stream()
                .map(todoList -> convertTodoListDTToTodoList(todoList))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        todoListRepository.deleteById(id);
    }

    public TodoListDTO save(TodoListDTO todoListDTO) {
        TodoList todoList = todoListRepository.save(convertTodoListToTodoListDTO(todoListDTO));
        return convertTodoListDTToTodoList(todoList);
    }

    public Optional<TodoListDTO> findById(Long id) {
        Optional<TodoList> todoList = todoListRepository.findById(id);

        if (todoList.isPresent()) {
            return Optional.of(convertTodoListDTToTodoList(todoList.get()));
        }

        return Optional.empty();
    }

    @Override
    public TodoListDTO update(TodoListDTO todoListDTO) {
        TodoList todoList = todoListRepository.save(convertTodoListToTodoListDTO(todoListDTO));
        return convertTodoListDTToTodoList(todoList);
    }


    private TodoList convertTodoListToTodoListDTO(TodoListDTO todoListDTO) {
        TodoList todoList = new TodoList();
        todoList.setId(todoListDTO.getId());
        todoList.setName(todoListDTO.getName());
        return todoList;
    }

    private TodoListDTO convertTodoListDTToTodoList(TodoList todoList) {
        TodoListDTO todoListDTO = new TodoListDTO();
        todoListDTO.setId(todoList.getId());
        todoListDTO.setName(todoList.getName());
        return todoListDTO;
    }


}