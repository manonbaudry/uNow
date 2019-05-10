package com.uNow.controller;

import com.uNow.UNowApplication;
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
public class FriendShipControllerTest {

    @LocalServerPort
    private int port;

    private URL baseURL;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private UserRepository userRepository;


    @Before
    public void setUp() throws MalformedURLException {
        this.baseURL = new URL("http://localhost:" + port + "/friendShip");
    }


    @Test
    public void whenCreateFriendShip_ThenReturnNewFriendShip() {
        HttpEntity<FriendShip> httpEntity = new HttpEntity<>(createFriendShip());
        ResponseEntity<FriendShip[]> responseEntity = template.getForEntity(baseURL.toString(), FriendShip[].class);
        assertEquals(0, responseEntity.getBody().length);

        template.postForObject(baseURL.toString(), httpEntity, FriendShip.class);
        responseEntity = template.getForEntity(baseURL.toString(), FriendShip[].class);
        assertEquals(1, responseEntity.getBody().length);
    }

    public FriendShip createFriendShip() {
        User userFrom = userRepository.findById(1);
        User userTo = userRepository.findById(2);
        return new FriendShip(userFrom, userTo);
    }
}