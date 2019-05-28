package com.uNow.repositories;

import com.uNow.entities.FriendRequest;
import com.uNow.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    List<FriendRequest> findByUserFrom(User userFrom);

    List<FriendRequest> findByUserTo(User userTo);
}
