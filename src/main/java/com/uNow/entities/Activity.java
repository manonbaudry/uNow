package com.uNow.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
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

}
