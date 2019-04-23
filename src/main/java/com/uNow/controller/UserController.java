package com.uNow.controller;

import com.uNow.entities.User;
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
    @GetMapping("/{id}")
    public User findById(@PathVariable long id) {
        return userRepository.getOne(id);
    }
// Vérification jamais même adresse mail?
    @CrossOrigin
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @CrossOrigin
    @PostMapping
    public User createUser(@RequestBody User user) {
        if(userRepository.findByEmail(user.getEmail()) == null)
            return userRepository.save(user);
        else
            return null;
    }
}
