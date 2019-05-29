package com.uNow.controller;


import com.uNow.entities.Activity;
import com.uNow.entities.User;
import com.uNow.exceptions.ActivityNotFoundException;
import com.uNow.exceptions.BadRequestException;
import com.uNow.exceptions.UserNotFoundException;
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
    public Activity findById(@PathVariable("activityId") long activityId) throws ActivityNotFoundException {
        if (activityRepository.findById(activityId).get() == null)
            throw new ActivityNotFoundException();
        return activityRepository.findById(activityId).get();
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
    @PutMapping
    public void addLike(@RequestBody Activity activity) throws BadRequestException {
        if (!activityRepository.existsById(activity.getId()))
            throw new BadRequestException();
        activity.setLikes(activity.getLikes() + 1);
        activityRepository.save(activity);
    }


    @CrossOrigin
    @DeleteMapping("/{activityId}")
    public void deleteActivity(@PathVariable("activityId") long activityId) throws ActivityNotFoundException {
        Activity activityToDelete = activityRepository.findById(activityId).get();
        if (activityToDelete != null)
            activityRepository.delete(activityToDelete);
        throw new ActivityNotFoundException();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void userNotFoundHandler(UserNotFoundException e) {
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void activityNotFoundHandler(ActivityNotFoundException e) {
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void badRequestHandler(ActivityNotFoundException e) {
    }

}
