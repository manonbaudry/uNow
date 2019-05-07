package com.uNow.entities;


import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@RequiredArgsConstructor
public class Friends {

    @Id
    @GeneratedValue
    private long id;

    @NonNull
    @ManyToOne
    private User user1;

    @NonNull
    @ManyToOne
    private User user2;

}
