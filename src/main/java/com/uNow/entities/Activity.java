package com.uNow.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Activity {

    @Id
    @GeneratedValue
    private long id;

    @NonNull
    @ManyToOne
    private User user;

    @NonNull
    private ActivityType type;

    @NonNull
    private Date date;

    @NonNull
    private String currentLocation;

    @NonNull
    private int likes;

    private String activityDetail;

}
