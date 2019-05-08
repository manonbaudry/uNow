package com.uNow.controller;

import com.uNow.entities.FriendShip;
import com.uNow.repositories.FriendShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendShipController {

    @Autowired
    FriendShipRepository friendShipRepository;

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

}
