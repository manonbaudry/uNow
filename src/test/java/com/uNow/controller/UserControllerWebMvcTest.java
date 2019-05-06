package com.uNow.controller;

import com.uNow.entities.User;
import com.uNow.repositories.ActivityRepository;
import com.uNow.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActivityRepository activityRepository;

    @MockBean
    private UserRepository userRepository;

    private User u;

    @Before
    public void init() {
        u = new User("Jonathan", "Wadin", "wadin.jonathan@gmail.com", "azerty", "7 rue du Levrier", "0000000000", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        //userRepository.save(new User("Jonathan", "Wadin", "wadin.jonathan@gmail.com", "azerty", "7 rue du Levrier", "0000000000", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }
    @Test
    public void whenUpdateUserInformation_ThenUserIsUpdated() throws Exception {
      /*  given(userRepository.findById(anyLong())).willReturn(u);
        //u.setEmail("man.baudry@gmail.com");
        ArrayList<User> tmp = new ArrayList<>();
        tmp.add(u);
        u.setFriends(tmp);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/create-friend", u))
            .andExpect(status().isOk());
            //.andExpect(jsonPath("email").value("man.baudry@gmail.com"));


    /*
    Test Get request :

        mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
                .andExpect(status().isOk())
               .andExpect(jsonPath("firstName").value("Jonathan"));
    */
    }

    @Test
    public void whenFindUserByIdWhoDoesntExist_ThenReturn404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/3"))
                .andExpect(status().isNotFound());
    }
}