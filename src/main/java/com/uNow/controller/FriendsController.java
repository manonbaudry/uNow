package com.uNow.controller;

import com.uNow.entities.Friends;
import com.uNow.entities.User;
import com.uNow.repositories.FriendsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    FriendsRepository friendsRepository;

    @CrossOrigin
    @GetMapping
    public List <Friends> getAll(){
        return friendsRepository.findAll();
    }

    @CrossOrigin
    @PostMapping
    public Friends createFriendship(@RequestBody Friends friends){
        return friendsRepository.save(friends);
    }

}
