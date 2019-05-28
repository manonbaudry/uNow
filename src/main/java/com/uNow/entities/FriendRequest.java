package com.uNow.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class FriendRequest {
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
