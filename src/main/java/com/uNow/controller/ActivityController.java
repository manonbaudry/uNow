package com.uNow.controller;


import com.uNow.entities.Activity;
import com.uNow.repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    @GetMapping
    public List<Activity> getAll(){
        return activityRepository.findAll();
    }

    @PostMapping
    public Activity createActivity(@RequestBody Activity activity){
        return activityRepository.save(activity);
    }
}
