package com.uNow.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


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

    private String refreshToken;


    public User(String firstName, String lastName, String email, String password, String location, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.refreshToken = null;
    }

    public User updateWith(User userUpdated) {
        if (userUpdated.getFirstName() != null)
            this.firstName = userUpdated.getFirstName();
        if (userUpdated.getLastName() != null)
            this.lastName = userUpdated.getLastName();
        if (userUpdated.getEmail() != null)
            this.email = userUpdated.getEmail();
        if (userUpdated.getPassword() != null)
            this.password = userUpdated.getPassword();
        if (userUpdated.getLocation() != null)
            this.location = userUpdated.getLocation();
        if (userUpdated.getPhoneNumber() != null)
            this.phoneNumber = userUpdated.getPhoneNumber();

        return this;
    }
}
