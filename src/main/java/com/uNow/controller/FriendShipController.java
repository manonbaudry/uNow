package com.uNow.controller;

import com.uNow.entities.FriendShip;
import com.uNow.exceptions.UserNotFoundException;
import com.uNow.repositories.FriendShipRepository;
import com.uNow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<FriendShip> getAllFriendsByUser(@PathVariable("userId") Long id) throws UserNotFoundException {
        if (friendShipRepository.findByUserFrom(userRepository.findById(id).get()) != null)
            return friendShipRepository.findByUserFrom(userRepository.findById(id).get());
        else if (friendShipRepository.findByUserTo(userRepository.findById(id).get()) != null)
            return friendShipRepository.findByUserTo(userRepository.findById(id).get());
        else throw new UserNotFoundException();
    }



}
