package com.todoapp.service;

import com.todoapp.entity.TodoList;
import com.todoapp.entity.User;
import com.todoapp.entity.model.TodoListDTO;
import com.todoapp.repository.TodoListRepository;
import com.todoapp.repository.UserRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TodoListServiceImpl implements TodoListService{

    private TodoListRepository todoListRepository;

    private UserRepository userRepoitory;

    public List<TodoListDTO> findAll(String name) {
        Optional<User> user = userRepoitory.findByUsername(name);

        if (user.isPresent()) {
            return todoListRepository.findByUser(user.get())
                    .stream()
                    .map(todoList -> convertTodoListDTToTodoList(todoList))
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Transactional
    public void delete(Long id) {
        todoListRepository.deleteById(id);
    }

    @Transactional
    public TodoListDTO save(String username, TodoListDTO todoListDTO) throws NotFoundException {
        Optional<User> user = userRepoitory.findByUsername(username);
        if (user.isPresent()) {
            TodoList todoList = todoListRepository.save(convertTodoListToTodoListDTO(user.get(), todoListDTO));
            return convertTodoListDTToTodoList(todoList);
        } else {
            throw new NotFoundException("User with such username does not exist");
        }

    }

    public Optional<TodoListDTO> findById(Long id) {
        Optional<TodoList> todoList = todoListRepository.findById(id);

        if (todoList.isPresent()) {
            return Optional.of(convertTodoListDTToTodoList(todoList.get()));
        }

        return Optional.empty();
    }

    @Transactional
    @Override
    public TodoListDTO update(TodoListDTO todoListDTO) {
        Optional<TodoList> todoList = todoListRepository.findById(todoListDTO.getId());

        if (todoList.isPresent()) {
            todoList.get().setName(todoListDTO.getName());
        }

        return convertTodoListDTToTodoList(todoListRepository.save(todoList.get()));
    }


    private TodoList convertTodoListToTodoListDTO(User user, TodoListDTO todoListDTO) {
        TodoList todoList = new TodoList();
        todoList.setId(todoListDTO.getId());
        todoList.setName(todoListDTO.getName());
        todoList.setUser(user);
        return todoList;
    }

    private TodoListDTO convertTodoListDTToTodoList(TodoList todoList) {
        TodoListDTO todoListDTO = new TodoListDTO();
        todoListDTO.setId(todoList.getId());
        todoListDTO.setName(todoList.getName());
        return todoListDTO;
    }


}
