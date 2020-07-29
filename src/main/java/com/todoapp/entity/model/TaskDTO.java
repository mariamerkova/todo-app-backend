package com.todoapp.entity.model;

import lombok.Data;

import javax.persistence.Column;

@Data
public class TaskDTO {

    private Long id;
    private String name;
    private String description;
}
