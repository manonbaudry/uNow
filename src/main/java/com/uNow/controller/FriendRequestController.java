package com.uNow.controller;

import com.uNow.entities.FriendRequest;
import com.uNow.entities.FriendShip;
import com.uNow.entities.User;
import com.uNow.exceptions.IdNotFoundException;
import com.uNow.repositories.FriendRequestRepository;
import com.uNow.repositories.FriendShipRepository;
import com.uNow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/friendRequest")
public class FriendRequestController {

    @Autowired
    FriendRequestRepository friendRequestRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendShipRepository friendShipRepository;

    @CrossOrigin
    @GetMapping
    public List<FriendRequest> getAll() {
        return friendRequestRepository.findAll();
    }

    @CrossOrigin
    @PostMapping
    public FriendRequest createFriendship(@RequestBody FriendRequest friendRequest) {
        return friendRequestRepository.save(friendRequest);
    }

    @CrossOrigin
    @GetMapping("/{userId}")
    public List<User> getAllFriendRequestByUser(@PathVariable("userId") long userId) throws IdNotFoundException {
        List<FriendRequest> userFromFriendRequest;
        List<FriendRequest> userToFriendRequest;
        List<User> result = new ArrayList<>();
        boolean userExist = false;

        if (friendRequestRepository.findByUserFrom(userRepository.findById(userId)) != null) {
            userExist = true;
            userFromFriendRequest = friendRequestRepository.findByUserFrom(userRepository.findById(userId));
            for (FriendRequest friendRequest : userFromFriendRequest) {
                if (friendRequest.getUserFrom().getId() != userId) {
                    result.add(friendRequest.getUserFrom());
                } else {
                    result.add(friendRequest.getUserTo());
                }
            }
        }

        if (friendRequestRepository.findByUserTo(userRepository.findById(userId)) != null) {
            userExist = true;
            userToFriendRequest = friendRequestRepository.findByUserTo(userRepository.findById(userId));
            for (FriendRequest friendRequest : userToFriendRequest) {
                if (friendRequest.getUserFrom().getId() != userId) {
                    result.add(friendRequest.getUserFrom());
                } else {
                    result.add(friendRequest.getUserTo());
                }
            }
        }

        if (!userExist)
            throw new IdNotFoundException();

        return result;
    }

    @CrossOrigin
    @GetMapping("/by-friendRequest/{id}")
    public FriendRequest findById(@PathVariable("id") long id) throws IdNotFoundException {
        if (friendRequestRepository.findById(id) == null)
            throw new IdNotFoundException();
        return friendRequestRepository.findById(id).get();
    }

    @CrossOrigin
    @PutMapping
    public void acceptFriendRequest(@RequestBody FriendRequest friendRequest) {
        friendShipRepository.save(new FriendShip(friendRequest.getUserFrom(), friendRequest.getUserTo()));
        friendRequestRepository.delete(friendRequest);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void deleteFriendRequest(@PathVariable("id") long id) throws IdNotFoundException {
        FriendRequest friendRequest = friendRequestRepository.findById(id).get();
        if (friendRequest == null)
            throw new IdNotFoundException();
        friendRequestRepository.delete(friendRequest);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void idNotFoundHandler(IdNotFoundException e) {
    }

}
