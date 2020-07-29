package com.todoapp.entity;

import com.todoapp.entity.TodoList;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TASK")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_task")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="todo_list_id", referencedColumnName = "id_todo_list")
    private TodoList todoList;




}
