package com.uNow.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class SpotifyApi {

    @Id
    @GeneratedValue
    private long id;


    private String code;


    private String state;
}
