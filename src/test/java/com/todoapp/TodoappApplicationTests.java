package com.todoapp;

import com.todoapp.entity.TodoList;
import com.todoapp.repository.TodoListRepository;
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

    @Test
    void contextLoads() {
    }

    @Test
    @Transactional
    @Rollback
    public void testGetAllTodoLists() {
        TodoList todoList = new TodoList();
        todoList.setName("Mimi");

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
        TodoList todoList = new TodoList();
        todoList.setName("Mishi");
        todoList = todoListRepository.save(todoList);
        TodoList todoList1 = new TodoList();
        todoList1.setName("Mishito");
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
        TodoList todoList = new TodoList();
        todoList.setName("Vasko");
        todoList = todoListRepository.save(todoList);
        Long todoListId = todoList.getId();

        todoListRepository.delete(todoList);

        Optional<TodoList> expectedTodoList = todoListRepository.findById(todoListId);

       Assertions.assertThat(expectedTodoList.isEmpty()).isTrue();
    }



}
