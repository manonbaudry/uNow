package com.uNow.controller;

import com.uNow.entities.FriendShip;
import com.uNow.entities.User;
import com.uNow.exceptions.UserNotFoundException;
import com.uNow.repositories.FriendShipRepository;
import com.uNow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/friendShip")
public class FriendShipController {

    @Autowired
    FriendShipRepository friendShipRepository;

    @Autowired
    UserRepository userRepository;

    @CrossOrigin
    @GetMapping
    public List <FriendShip> getAll(){
        return friendShipRepository.findAll();
    }

    @CrossOrigin
    @PostMapping
    public FriendShip createFriendship(@RequestBody FriendShip friendShip){
        return friendShipRepository.save(friendShip);
    }

    @CrossOrigin
    @GetMapping("/{userId}")
    public List<User> getAllFriendsByUser(@PathVariable("userId") Long id) throws UserNotFoundException {
        List<FriendShip> userFromFriendship;
        List<FriendShip> userToFriendship;
        List<User> result = new ArrayList<>();
        boolean userExist = false;

        if (friendShipRepository.findByUserFrom(userRepository.findById(id).get()) != null) {
            userExist = true;
            userFromFriendship = friendShipRepository.findByUserFrom(userRepository.findById(id).get());
            for (FriendShip friendship : userFromFriendship) {
                if (friendship.getUserFrom().getId() != id) {
                    result.add(friendship.getUserFrom());
                } else {
                    result.add(friendship.getUserTo());
                }
            }
        }

        if (friendShipRepository.findByUserTo(userRepository.findById(id).get()) != null) {
            userExist = true;
            userToFriendship = friendShipRepository.findByUserTo(userRepository.findById(id).get());
            for (FriendShip friendship : userToFriendship) {
                if (friendship.getUserFrom().getId() != id) {
                    result.add(friendship.getUserFrom());
                } else {
                    result.add(friendship.getUserTo());
                }
            }
        }

        if (!userExist)
            throw new UserNotFoundException();

        return result;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void userNotFoundHandler(UserNotFoundException e) {
    }

}
