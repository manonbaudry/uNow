package com.uNow.controller;

import com.uNow.repositories.ActivityRepository;
import com.uNow.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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


    @Test
    public void whenUpdateUserInformation_ThenUserIsUpdated() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
                .andExpect(status().isOk());
               // .andExpect(jsonPath("firstName").value("Jojo"));
    }

    @Test
    public void whenFindUserByIdWhoDoesntExist_ThenReturn404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/3"))
                .andExpect(status().isNotFound());
    }
}