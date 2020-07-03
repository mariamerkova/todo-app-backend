package com.todoapp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TODO_LIST")
@Data
public class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_todo_list")
    private Long id;

    @Column(name = "name")
    private String name;
}
