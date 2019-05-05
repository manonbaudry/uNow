package com.uNow.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    private List<User> friends;

    @NonNull
    @OneToMany
    private List<Activity> activities;

    //Créer un nouvel objet

    @NonNull
    @OneToMany
    private List<User> friendsRequest;
}
