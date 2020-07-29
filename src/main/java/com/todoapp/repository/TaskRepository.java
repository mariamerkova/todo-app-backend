package com.todoapp.repository;

import com.todoapp.entity.Task;
import com.todoapp.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllTasksByTodoListId(Long id);
}
