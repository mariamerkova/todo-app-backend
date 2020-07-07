package com.todoapp;

import com.todoapp.entity.TodoList;
import com.todoapp.entity.User;
import com.todoapp.repository.TodoListRepository;
import com.todoapp.repository.UserRepoitory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest
class TodoappApplicationTests {

    @Autowired
    private TodoListRepository todoListRepository;

    @Autowired
    private UserRepoitory userRepoitory;

    @Test
    void contextLoads() {
    }

    @Test
    @Transactional
    @Rollback
    public void testGetAllTodoLists() {
        User user = new User();
        user.setPassword("mimito");
        user.setActive(true);
        user.setUsername("mimito");
        user.setRoles("manager");

        userRepoitory.save(user);
        TodoList todoList = new TodoList();
        todoList.setName("Mimi");
        todoList.setUser(user);
        todoList = todoListRepository.save(todoList);
        Long todoListId = todoList.getId();
        todoList = null;
        todoList = todoListRepository.getOne(todoListId);

        Assertions.assertThat(todoListRepository.findAll().size()).isEqualTo(1);

        Assertions.assertThat(todoList).isNotNull();
        Assertions.assertThat(todoList.getName()).isEqualTo("Mimi");
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateTodoList() {
        User user = new User();
        user.setPassword("mimito");
        user.setActive(true);
        user.setUsername("mimito");
        user.setRoles("manager");
        userRepoitory.save(user);

        TodoList todoList = new TodoList();
        todoList.setName("Mishi");
        todoList.setUser(user);
        todoList = todoListRepository.save(todoList);
        TodoList todoList1 = new TodoList();
        todoList1.setName("Mishito");
        todoList1.setUser(user);
        todoList = todoListRepository.save(todoList1);

        Long todoListId = todoList.getId();
        todoList = null;
        todoList = todoListRepository.getOne(todoListId);

        Assertions.assertThat(todoList).isNotNull();
        Assertions.assertThat(todoList.getName()).isEqualTo("Mishito");

    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteTodoList() {
        User user = new User();
        user.setPassword("mimito");
        user.setActive(true);
        user.setUsername("mimito");
        user.setRoles("manager");
        userRepoitory.save(user);

        TodoList todoList = new TodoList();
        todoList.setName("Vasko");
        todoList.setUser(user);
        todoList = todoListRepository.save(todoList);
        Long todoListId = todoList.getId();

        todoListRepository.delete(todoList);

        Optional<TodoList> expectedTodoList = todoListRepository.findById(todoListId);

       Assertions.assertThat(expectedTodoList.isEmpty()).isTrue();
    }



}
