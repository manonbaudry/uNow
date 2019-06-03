package com.uNow.controller;


import com.uNow.entities.Activity;
import com.uNow.entities.User;
import com.uNow.exceptions.BadRequestException;
import com.uNow.exceptions.IdNotFoundException;
import com.uNow.repositories.ActivityRepository;
import com.uNow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
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
    @GetMapping("/by-activity/{activityId}")
    public Activity findById(@PathVariable("activityId") long activityId) throws IdNotFoundException {
        if (activityRepository.findById(activityId).get() == null)
            throw new IdNotFoundException();
        return activityRepository.findById(activityId).get();
    }

    @CrossOrigin
    @GetMapping("/{userId}")
    public List<Activity> findAllByUser(@PathVariable("userId") Long userId) throws IdNotFoundException {
        User user = userRepository.findById(userId).get();
        if(activityRepository.findByUser(user) == null)
            throw new IdNotFoundException();
        return activityRepository.findByUser(user);
    }


    @CrossOrigin
    @PutMapping
    public void addLike(@RequestBody Activity activity) throws BadRequestException {
        if (!activityRepository.existsById(activity.getId()))
            throw new BadRequestException();
        activity.setLikes(activity.getLikes() + 1);
        activityRepository.save(activity);
    }


    @CrossOrigin
    @DeleteMapping("/{activityId}")
    public void deleteActivity(@PathVariable("activityId") long activityId) throws IdNotFoundException {
        Activity activityToDelete = activityRepository.findById(activityId).get();
        if (activityToDelete != null)
            activityRepository.delete(activityToDelete);
        throw new IdNotFoundException();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void idNotFoundHandler(IdNotFoundException e) {
    }


}
