package com.todoapp.api;

import com.todoapp.entity.model.UserRegister;
import com.todoapp.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationRestApi {
    private final AuthenticationService authenticationService;

    @PostMapping(path = "/register")
    public boolean register(@RequestBody UserRegister userRegister) {
        return authenticationService.register(userRegister);
    }

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }
}
