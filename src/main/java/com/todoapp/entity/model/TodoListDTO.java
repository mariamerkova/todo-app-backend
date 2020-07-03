package com.todoapp.entity.model;

import lombok.Data;

import javax.persistence.Column;

@Data
public class TodoListDTO {

    private Long id;
    private String name;
}
