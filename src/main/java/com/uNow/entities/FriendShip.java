package com.uNow.entities;


import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@RequiredArgsConstructor
public class FriendShip {

    @Id
    @GeneratedValue
    private long id;

    @NonNull
    @ManyToOne
    private User userFrom;

    @NonNull
    @ManyToOne
    private User userTo;

}
