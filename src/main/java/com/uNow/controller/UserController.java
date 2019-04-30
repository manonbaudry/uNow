package com.uNow.controller;

import com.uNow.entities.User;
import com.uNow.exceptions.AlreadyExistEmailException;
import com.uNow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/id")
    public User findById(@PathVariable long id) {
        return userRepository.findById(id);
    }


    @CrossOrigin
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @CrossOrigin
    @PostMapping
    public User createUser(@RequestBody User user) throws AlreadyExistEmailException {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            userRepository.save(user);
            return user;
        }
        throw new AlreadyExistEmailException("The given email is already in use");

    }
}
