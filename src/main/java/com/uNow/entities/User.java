package com.uNow.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private String location;

    @NonNull
    private String phoneNumber;


    @NonNull
    @OneToMany
    private Set<User> friends;

    @NonNull
    @OneToMany
    private Set<Activity> activities;

    @NonNull
    @OneToMany
    private Set<User> friendsRequest;
}
