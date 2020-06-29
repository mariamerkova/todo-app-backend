package com.todoapp.service;

import com.todoapp.entity.model.UserRegister;

import java.util.Optional;

public interface AuthenticationService {

    boolean register(UserRegister userRegister);
}
