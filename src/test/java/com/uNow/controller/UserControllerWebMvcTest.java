package com.uNow.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URL;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerWebMvcTest {

    @LocalServerPort
    private int port;

    private URL baseURL;

    @Autowired
    private MockMvc mockMvc;



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