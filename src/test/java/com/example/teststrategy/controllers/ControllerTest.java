package com.example.teststrategy.controllers;

import com.example.teststrategy.models.UserInfo;
import com.example.teststrategy.request.NewUserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createNewUser_ValidRequest_ShouldReturnOK() throws Exception {
        NewUserRequest newUserRequest = new NewUserRequest("han@solo.com", "mIttpw!12", "han", 25);

        this.mockMvc.perform(post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(
                                newUserRequest
                        )))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        new ObjectMapper().writeValueAsString(
                                new UserInfo(1,"han",25, 1)
                        )
                ));
    }
    @Test
    void createNewUser_InvalidRequest_ShouldReturnBAD() throws Exception {
        NewUserRequest newUserRequest = new NewUserRequest("asd", "123", "han", -25);

        this.mockMvc.perform(post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(
                                newUserRequest
                        )))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(
                        new ObjectMapper().writeValueAsString(
                                new UserInfo()
                        )
                ));
    }

    @Test
    void getBalance() {
    }

    @Test
    void createNewUser() {
    }

    @Test
    void updateBalance() {
    }
}