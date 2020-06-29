package com.todoapp.service;

import com.todoapp.entity.User;
import com.todoapp.entity.model.UserRegister;
import com.todoapp.exception.UserAlreadyExistException;
import com.todoapp.repository.UserRepoitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepoitory userRepoitory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public boolean register(UserRegister userRegister) {
         Optional<User> user = userRepoitory.findByUsername(userRegister.getUsername());
         if (user.isPresent()) {
             throw new UserAlreadyExistException(String.format("User with username %s already exist", userRegister.getUsername()));
         }

         User newUser = new User();
         newUser.setActive(true);
         newUser.setUsername(userRegister.getUsername());
         newUser.setRoles("ROLE_USER");
         newUser.setPassword(passwordEncoder.encode(userRegister.getPassword()));

         userRepoitory.save(newUser);

         return true;
    }
}
