package com.uNow.controller;

import com.uNow.UNowApplication;
import com.uNow.entities.User;
import org.apache.tomcat.util.log.SystemLogHandler;
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
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UNowApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserControllerSpringBootTests {

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
    public void whenCreateUser_ThenReturnNewUser() {


       HttpEntity<User> userHttpEntity = new HttpEntity<>(createUser("jonathan.wadin@gmail.com"));

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
        HttpEntity<User> userHttpEntity = new HttpEntity<>(createUser("man.baudry@gmail.com"));
        template.postForObject(baseURL.toString(), userHttpEntity, User.class);
        ResponseEntity<User[]> response = template.getForEntity(baseURL.toString(), User[].class);
        assertEquals(3, response.getBody().length);
    }

    @Test
    public void whenUpdateUserInformation_ThenUserIsUpdated(){
        /*ResponseEntity <User> response = template.getForEntity(baseURL.toString() + "/1", User.class);
        User userToUpdate = response.getBody();
        userToUpdate.setEmail("test@toto.fr");
        System.out.println("ID UserToUpdate :" + userToUpdate.getId());

        template.put(baseURL.toString(), userToUpdate);

        response = template.getForEntity(baseURL.toString() + "/1", User.class);
        System.out.println(response.getBody().toString());
        assertEquals("test@toto.fr", response.getBody().getEmail());*/

    }

    public User createUser(String email){
        return new User("Jackie", "Kennedy", email, "azerty", "Saint Amand", "0631440224", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }



}
