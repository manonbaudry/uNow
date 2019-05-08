package com.uNow.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String location;

    private String phoneNumber;


    public User(String firstName, String lastName, String email, String password, String location, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.location = location;
        this.phoneNumber = phoneNumber;
    }

    public User updateWith(User userUpdate){
        return null;
    }
}
