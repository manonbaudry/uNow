package com.uNow.repositories;

import com.uNow.entities.Activity;
import com.uNow.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByUser(User u);
}
