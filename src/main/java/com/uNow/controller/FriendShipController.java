package com.uNow.controller;

import com.uNow.entities.FriendShip;
import com.uNow.entities.User;
import com.uNow.exceptions.IdNotFoundException;
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
    public List<User> getAllFriendsByUser(@PathVariable("userId") Long userId) throws IdNotFoundException {
        if (!userRepository.existsById(userId))
            throw new IdNotFoundException();

        List<FriendShip> userFromFriendship;
        List<FriendShip> userToFriendship;
        List<User> result = new ArrayList<>();

        if (friendShipRepository.findByUserFrom(userRepository.findById(userId).get()) != null) {
            userFromFriendship = friendShipRepository.findByUserFrom(userRepository.findById(userId).get());
            for (FriendShip friendship : userFromFriendship) {
                if (friendship.getUserFrom().getId() != userId) {
                    result.add(friendship.getUserFrom());
                } else {
                    result.add(friendship.getUserTo());
                }
            }
        }

        if (friendShipRepository.findByUserTo(userRepository.findById(userId).get()) != null) {
            userToFriendship = friendShipRepository.findByUserTo(userRepository.findById(userId).get());
            for (FriendShip friendship : userToFriendship) {
                if (friendship.getUserFrom().getId() != userId) {
                    result.add(friendship.getUserFrom());
                } else {
                    result.add(friendship.getUserTo());
                }
            }
        }

        return result;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void idNotFoundHandler(IdNotFoundException e) {
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void deleteFriendShip(@PathVariable("id") long id) throws IdNotFoundException {
        FriendShip friendShip = friendShipRepository.findById(id).get();
        if (friendShip == null)
            throw new IdNotFoundException();
        friendShipRepository.delete(friendShip);
    }
}
