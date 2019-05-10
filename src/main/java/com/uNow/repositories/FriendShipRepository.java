package com.uNow.repositories;

import com.uNow.entities.FriendShip;
import com.uNow.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FriendShipRepository extends JpaRepository<FriendShip, Long> {

    List<FriendShip> findByUserFrom(User userFrom);

    List<FriendShip> findByUserTo(User userTo);

}
