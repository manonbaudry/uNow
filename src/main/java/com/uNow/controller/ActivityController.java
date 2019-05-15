package com.uNow.controller;


import com.uNow.entities.Activity;
import com.uNow.entities.User;
import com.uNow.exceptions.UserNotFoundException;
import com.uNow.repositories.ActivityRepository;
import com.uNow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;


    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @PostMapping
    public Activity createActivity(@RequestBody Activity activity){
        return activityRepository.save(activity);
    }

    @CrossOrigin
    @GetMapping
    public List<Activity> getAll(){
        return  activityRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/{userId}")
    public List<Activity> findAllByUser(@PathVariable("userId") Long userId )  throws UserNotFoundException {
        User user = userRepository.findById(userId).get();
        if(activityRepository.findByUser(user) == null)
            throw new UserNotFoundException();
        return activityRepository.findByUser(user);
    }

    @CrossOrigin
    @DeleteMapping("/{activityId}")
    public void deleteActivity(@PathVariable("activityId") long activityId) {
        Activity activityToDelete = activityRepository.findById(activityId).get();
        activityRepository.delete(activityToDelete);
    }
}
