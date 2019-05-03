package com.uNow.controller;

import com.uNow.UNowApplication;
import com.uNow.entities.User;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UNowApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    private URL baseURL;

    @Autowired
    private TestRestTemplate template;


    @Before
    public void setUp() throws MalformedURLException {
        this.baseURL = new URL("http://localhost:" + port + "/user");
    }

    @Test
    public void whenCreateUser_ThenReturnNewUser(){
        HttpEntity<User> userHttpEntity = new HttpEntity<>(new User("Jonathan", "Wadin", "lunaat@gmail.com", "azerty", "7 rue du Levrier", "0000000000", null, null, null));

        ResponseEntity<User[]> response = template.getForEntity(baseURL.toString(), User[].class);
        assertEquals(3, response.getBody().length);

        template.postForObject(baseURL.toString(), userHttpEntity, User.class);

        response = template.getForEntity(baseURL.toString(), User[].class);
        assertEquals(4, response.getBody().length);
    }

    @Test
    public void whenFindUserById_ThenReturnUser() {
        ResponseEntity<String> response = template.getForEntity(baseURL.toString() + "/1", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void whenCreateUserWithAlreadyExistEmail_ThenThrowException() {
        ResponseEntity<User[]> response1 = template.getForEntity(baseURL.toString(), User[].class);
        System.out.println(response1.getBody().length);
        HttpEntity<User> userHttpEntity = new HttpEntity<>(new User("Jackie", "Kennedy", "man.baudry@gmail.com", "azerty", "Saint Amand", "0631440224", null, null, null));
        template.postForObject(baseURL.toString(), userHttpEntity, User.class);

        ResponseEntity<User[]> response = template.getForEntity(baseURL.toString(), User[].class);
        assertEquals(4, response.getBody().length);
    }
    /*
    @Test
    public void whenUpdateUserInformation_ThenUserIsUpdated(){
        HttpEntity<User> userHttpEntity = new HttpEntity<>(new User("Jonathan", "Wadin", "wadin.jonathan@gmail.com", "azerty" ,"7 rue du Levrier", "0000000000", null, null, null ));
        template.put(baseURL.toString(), userHttpEntity, User.class);

    }*/

}
