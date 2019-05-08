package com.uNow.controller;

import com.uNow.entities.User;
import com.uNow.exceptions.AlreadyExistEmailException;
import com.uNow.exceptions.UserNotFoundException;
import com.uNow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public User findById(@PathVariable("id") long id) throws UserNotFoundException {
        if (userRepository.findById(id) == null) {
            throw new UserNotFoundException();
        }
        return userRepository.findById(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void userNotFoundHandler(UserNotFoundException e){}

    @CrossOrigin
    @PostMapping
    public User createUser(@RequestBody User user) throws AlreadyExistEmailException {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            userRepository.save(user);
            return user;
        }
        throw new AlreadyExistEmailException("The given email is already in use");
    }

    @CrossOrigin
    @PutMapping
    public User updateUserInformation(@RequestBody User user) {
        User userToUpdate = userRepository.findById(user.getId());
        if(user.getFirstName()!= null) {
            userToUpdate.setFirstName(user.getFirstName());
        }
        if(user.getLastName() != null) {
            userToUpdate.setLastName(user.getLastName());
            userRepository.findById(user.getId()).setLastName(user.getLastName());
        }
        if(user.getEmail() != null) {
            userToUpdate.setEmail(user.getEmail());
            userRepository.findById(user.getId()).setEmail(user.getEmail());
        }
        if(user.getPassword() != null) {
            userToUpdate.setPassword(user.getPassword());
            userRepository.findById(user.getId()).setPassword(user.getPassword());
        }
        if(user.getPhoneNumber() != null) {
            userToUpdate.setPhoneNumber(user.getPhoneNumber());
            userRepository.findById(user.getId()).setPhoneNumber(user.getPhoneNumber());
        }
        if(user.getLocation() != null) {
            userToUpdate.setLocation(user.getLocation());
            userRepository.findById(user.getId()).setLocation(user.getLocation());
        }
         userRepository.save(userToUpdate);
        return userRepository.findById(user.getId());
    }
}
