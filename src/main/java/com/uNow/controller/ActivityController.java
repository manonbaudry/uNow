package com.uNow.controller;


import com.uNow.entities.Activity;
import com.uNow.entities.User;
import com.uNow.exceptions.UserNotFoundException;
import com.uNow.repositories.ActivityRepository;
import com.uNow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}")
    public List<Activity> findAllByUser(@PathVariable("userId") Long userId )  throws UserNotFoundException {
        User u = userRepository.getOne(userId);
        if(activityRepository.findByUser(u) == null)
            throw new UserNotFoundException();
        return activityRepository.findByUser(u);
    }

    @CrossOrigin
    @DeleteMapping("/{activityId}")
    public void deleteActivity(@PathVariable("activityId") long activityId) {
        activityRepository.deleteById(activityId);
    }

    @PostMapping
    public Activity createActivity(@RequestBody Activity activity){
        return activityRepository.save(activity);
    }

}
