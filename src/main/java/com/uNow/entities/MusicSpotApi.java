package com.uNow.entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity
public class MusicSpotApi {

    @Id
    @GeneratedValue
    private long id;


    private String code;


    private String state;

    private String refreshToken;
}
