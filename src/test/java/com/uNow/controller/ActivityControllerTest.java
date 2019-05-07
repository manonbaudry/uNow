package com.uNow.controller;

import com.uNow.UNowApplication;
import com.uNow.entities.Activity;
import com.uNow.entities.ActivityType;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UNowApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActivityControllerTest {

    @LocalServerPort
    private int port;

    private URL baseURL;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws MalformedURLException {
        this.baseURL = new URL("http://localhost:" + port + "/activity");
    }

    @Test
    public void whenCreateActivity_ThenReturnNewActivity(){

        HttpEntity<Activity> activityHttpEntity = new HttpEntity<>(createActivity());

        ResponseEntity<Activity[]> response = template.getForEntity(baseURL.toString(), Activity[].class);
        assertEquals(0, response.getBody().length);

        template.postForObject(baseURL.toString(), activityHttpEntity, Activity.class);

        response = template.getForEntity(baseURL.toString(), Activity[].class);
        assertEquals(1, response.getBody().length);

    }

    @Test
    public void whenFindAllByUser_ThenReturnListActivity(){
        ResponseEntity<String> r = template.getForEntity(baseURL.toString() + "/1" ,String.class);
        assertEquals(HttpStatus.OK, r.getStatusCode());

        HttpEntity<Activity> activityHttpEntity = new HttpEntity<>(createActivity());
        template.postForObject(baseURL.toString(), activityHttpEntity, Activity.class);

        ResponseEntity<Object[]> response = template.getForEntity(baseURL.toString() + "/1", Object[].class);
        assertEquals(1, response.getBody().length);
    }

    private Activity createActivity() {
        User user = userRepository.findById(1);
        return new Activity( user  , ActivityType.WORK, new Date());
    }


}