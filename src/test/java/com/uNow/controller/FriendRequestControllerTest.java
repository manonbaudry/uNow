package com.uNow.controller;

import com.uNow.UNowApplication;
import com.uNow.entities.FriendRequest;
import com.uNow.entities.FriendShip;
import com.uNow.entities.User;
import com.uNow.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = UNowApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FriendRequestControllerTest {

    @LocalServerPort
    private int port;

    private URL baseURL;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws MalformedURLException {
        this.baseURL = new URL("http://localhost:" + port);
    }

    @Test
    public void whenCreateFriendRequest_ThenReturnNewFriendRequest() {
        HttpEntity<FriendRequest> httpEntity = new HttpEntity<>(createFriendRequest(3, 4));
        ResponseEntity<FriendRequest[]> responseEntity = template.getForEntity(baseURL.toString() + "/friendRequest", FriendRequest[].class);
        assertEquals(1, responseEntity.getBody().length);

        template.postForObject(baseURL.toString() + "/friendRequest", httpEntity, FriendRequest.class);
        responseEntity = template.getForEntity(baseURL.toString() + "/friendRequest", FriendRequest[].class);
        assertEquals(2, responseEntity.getBody().length);
    }


    @Test
    public void whenGetAllFriendRequestsByUser_ThenReturnAllHisFriendRequests() {
        ResponseEntity<User[]> responseEntity = template.getForEntity(baseURL.toString() + "/friendRequest/4", User[].class);
        assertEquals(1, responseEntity.getBody().length);

    }

    @Test
    public void whenGetFriendRequest_ThenReturnTheFriendRequest() {
        ResponseEntity<FriendRequest> response = template.getForEntity(baseURL.toString() + "/friendRequest/getFriendRequest/11", FriendRequest.class);
        assertEquals(11, response.getBody().getId());
    }

    @Test
    public void whenFriendRequestIsAccepted_ThenFriendShipIsCreated() {
        ResponseEntity<FriendRequest> responseEntity = template.getForEntity(baseURL.toString() + "/friendRequest/getFriendRequest/11", FriendRequest.class);
        template.put(baseURL.toString() + "/friendRequest", responseEntity.getBody());

        ResponseEntity<FriendShip[]> responseFriendShip = template.getForEntity((baseURL.toString()) + "friendShip/4", FriendShip[].class);
        assertEquals(2, responseFriendShip.getBody().length);

    }

    @Test
    public void whenDeleteFriendRequest_ThenFriendRequestIsDeleted() {
        template.delete(baseURL.toString() + "/friendRequest/11");

        ResponseEntity<User[]> response = template.getForEntity(baseURL.toString() + "/friendRequest/4", User[].class);
        assertEquals(0, response.getBody().length);
    }

    public FriendRequest createFriendRequest(int id1, int id2) {
        User userFrom = userRepository.findById(id1);
        User userTo = userRepository.findById(id2);
        return new FriendRequest(userFrom, userTo);
    }

}