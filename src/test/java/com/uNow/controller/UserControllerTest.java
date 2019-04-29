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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

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

    @Autowired
    private Validator validator;

    @Before
    public void setUp() throws MalformedURLException {
        this.baseURL = new URL("http://localhost:" + port + "/user");
    }

    @Test
    public void whenCreateUser_ThenReturnNewUser(){
        HttpEntity<User> userHttpEntity = new HttpEntity<>(new User("Jonathan", "Wadin", "lunaat@gmail.com", "azerty", "7 rue du Levrier", "admin", "0000000000"));

        ResponseEntity<User[]> response = template.getForEntity(baseURL.toString(), User[].class);
        assertEquals(3, response.getBody().length);

        template.postForObject(baseURL.toString(), userHttpEntity, User.class);

        response = template.getForEntity(baseURL.toString(), User[].class);
        assertEquals(4, response.getBody().length);

    }

    @Test
    public void shouldValidateDuplicatedLogin() throws Exception {
        User newUser = new User("Jackie", "Kennedy", "man.baudry@ŋmail.com", "azerty", "Saint Amand", "lapin", "0631440224");
        Set<ConstraintViolation<User>> violations = validator.validate(newUser);
        // then
        assertEquals(1, violations.size());
    }

}
