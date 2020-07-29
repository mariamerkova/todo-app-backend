package com.todoapp;

import com.todoapp.entity.Task;
import com.todoapp.entity.TodoList;
import com.todoapp.entity.User;
import com.todoapp.repository.TaskRepository;
import com.todoapp.repository.TodoListRepository;
import com.todoapp.repository.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

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

        userRepository.save(user);
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
        userRepository.save(user);

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
        userRepository.save(user);

        TodoList todoList = new TodoList();
        todoList.setName("Vasko");
        todoList.setUser(user);
        todoList = todoListRepository.save(todoList);
        Long todoListId = todoList.getId();

        todoListRepository.delete(todoList);

        Optional<TodoList> expectedTodoList = todoListRepository.findById(todoListId);

       Assertions.assertThat(expectedTodoList.isEmpty()).isTrue();
    }

    @Test
    @Transactional
    @Rollback
    public void testGetAllTasks() {
        User user = new User();
        user.setPassword("mimito");
        user.setActive(true);
        user.setUsername("mimito");
        user.setRoles("manager");
        userRepository.save(user);

        TodoList todoList = new TodoList();
        todoList.setName("Vasko");
        todoList.setUser(user);
        todoList= todoListRepository.save(todoList);

        Task task = new Task();
        task.setDescription("Buy dress");
        task.setName("Dress");
        task.setTodoList(todoList);
        todoList.getTasks().add(task);

        task = taskRepository.save(task);

        Long taskId = task.getId();
        task = null;
        task = taskRepository.getOne(taskId);

        Assertions.assertThat(taskRepository.findAllTasksByTodoListId(todoList.getId()).size()).isEqualTo(1);
        Assertions.assertThat(task).isNotNull();
        Assertions.assertThat(task.getName()).isEqualTo("Dress");
    }


    @Test
    @Transactional
    @Rollback
    public void testDeleteTasks() {
        User user = new User();
        user.setPassword("mimito");
        user.setActive(true);
        user.setUsername("mimito");
        user.setRoles("manager");
        userRepository.save(user);

        TodoList todoList = new TodoList();
        todoList.setName("Vasko");
        todoList.setUser(user);
        todoList= todoListRepository.save(todoList);

        Task task1 = new Task();
        task1.setDescription("Buy dress");
        task1.setName("Dress");
        task1.setTodoList(todoList);

        task1 = taskRepository.save(task1);
        todoList.getTasks().add(task1);

        Task task2 = new Task();
        task2.setDescription("Buy lion");
        task2.setName("Lion");
        task2.setTodoList(todoList);
        todoList.getTasks().add(task2);

        task2 = taskRepository.save(task2);

        taskRepository.delete(task1);
        Assertions.assertThat(taskRepository.findAllTasksByTodoListId(todoList.getId()).size()).isEqualTo(1);
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateTasks() {
        User user = new User();
        user.setPassword("mimito");
        user.setActive(true);
        user.setUsername("mimito");
        user.setRoles("manager");
        userRepository.save(user);

        TodoList todoList = new TodoList();
        todoList.setName("Vasko");
        todoList.setUser(user);
        todoList= todoListRepository.save(todoList);

        Task task = new Task();
        task.setDescription("Buy dress");
        task.setName("Dress");
        task.setTodoList(todoList);
        todoList.getTasks().add(task);

        task = taskRepository.save(task);

        Task task1 = new Task();
        task1.setDescription("Buy skirt");
        task1.setName("Skirt");
        task1.setTodoList(todoList);
        todoList.getTasks().add(task1);

        task = taskRepository.save(task1);
        Long taskId = task.getId();
        task = null;
        task = taskRepository.getOne(taskId);

        Assertions.assertThat(task).isNotNull();
        Assertions.assertThat(task.getName()).isEqualTo("Skirt");
    }

}
