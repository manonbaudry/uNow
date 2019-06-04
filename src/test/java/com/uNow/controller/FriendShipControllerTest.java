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
import org.springframework.http.HttpStatus;
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
        HttpEntity<FriendShip> httpEntity = new HttpEntity<>(createFriendShip(1, 3));
        ResponseEntity<FriendShip[]> responseEntity = template.getForEntity(baseURL.toString(), FriendShip[].class);
        assertEquals(3, responseEntity.getBody().length);

        template.postForObject(baseURL.toString(), httpEntity, FriendShip.class);
        responseEntity = template.getForEntity(baseURL.toString(), FriendShip[].class);
        assertEquals(4, responseEntity.getBody().length);
    }

    @Test
    public void whenGetAllFriendsByUser_ThenReturnAllHisFriends() {
        HttpEntity<FriendShip> httpEntity = new HttpEntity<>(createFriendShip(4, 3));
        template.postForObject(baseURL.toString(), httpEntity, FriendShip.class);
        ResponseEntity<User[]> responseEntity = template.getForEntity(baseURL.toString() + "/3", User[].class);
        assertEquals(3, responseEntity.getBody().length);
    }

    @Test
    public void whenFindByIdWhoDoesntExist_ThenReturn404() {
        ResponseEntity<String> response = template.getForEntity(baseURL.toString() + "/friendShip/99", String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    public FriendShip createFriendShip(int id1, int id2) {
        User userFrom = userRepository.findById(id1);
        User userTo = userRepository.findById(id2);
        return new FriendShip(userFrom, userTo);
    }
}