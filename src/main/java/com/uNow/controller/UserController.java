package com.uNow.controller;

import com.uNow.entities.User;
import com.uNow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();

    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

}
