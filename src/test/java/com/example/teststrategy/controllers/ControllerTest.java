package com.example.teststrategy.controllers;

import com.example.teststrategy.models.Balance;
import com.example.teststrategy.models.UserInfo;
import com.example.teststrategy.request.LoginRequest;
import com.example.teststrategy.request.NewUserRequest;
import com.example.teststrategy.request.SetBalanceRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    void createNewUser_ValidRequest_ShouldReturnOK() throws Exception {
        this.mockMvc.perform(post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(
                                new NewUserRequest("han@solo.com", "mIttpw!12", "han", 25)

                        )))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        new ObjectMapper().writeValueAsString(
                                new UserInfo(2,"han",25, 2)
                        )
                ));
    }
    @Test
    void createNewUser_EmailAlreadyExist_ShouldReturnBAD() throws Exception {
        this.mockMvc.perform(post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(
                                new NewUserRequest("han@solo.com", "mIttpw!12", "han", 25)

                        )))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(
                        new ObjectMapper().writeValueAsString(
                                new UserInfo()
                        )
                ));
    }

    @Test
    void createNewUser_InvalidPassword_ShouldReturnBAD() throws Exception {
        this.mockMvc.perform(post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(
                                new NewUserRequest("hanna@solo.com", "asd", "hanna", 25)

                        )))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(
                        new ObjectMapper().writeValueAsString(
                                new UserInfo()
                        )
                ));
    }

    @Test
    void createNewUser_InvalidEmail_ShouldReturnBAD() throws Exception {
        this.mockMvc.perform(post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(
                                new NewUserRequest("asd", "mIttpw!12", "hanna", 25)

                        )))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(
                        new ObjectMapper().writeValueAsString(
                                new UserInfo()
                        )
                ));
    }

    @Test
    void createNewUser_InvalidName_ShouldReturnBAD() throws Exception {
        this.mockMvc.perform(post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(
                                new NewUserRequest("hanna@solo.com", "mIttpw!12", "b", 25)

                        )))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(
                        new ObjectMapper().writeValueAsString(
                                new UserInfo()
                        )
                ));
    }

    @Test
    void createNewUser_InvalidAge_ShouldReturnBAD() throws Exception {
        this.mockMvc.perform(post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(
                                new NewUserRequest("hanna@solo.com", "mIttpw!12", "bostrom", -25)

                        )))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(
                        new ObjectMapper().writeValueAsString(
                                new UserInfo()
                        )
                ));
    }

    @Test
    void loginUser_ValidRequest_ShouldReturnOK() throws Exception {
        this.mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(
                                new LoginRequest("han@solo.com", "mIttpw!12")
                        )))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        new ObjectMapper().writeValueAsString(
                                new UserInfo(2,"han",25, 2)
                        )
                ));
    }

    @Test
    void loginUser_InvalidPassword_ShouldReturnBAD() throws Exception {
        this.mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(
                                new LoginRequest("han@solo.com", "wrongPassword!")

                        )))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(
                        new ObjectMapper().writeValueAsString(
                                new UserInfo()
                        )
                ));
    }

    @Test
    void setBalance_ValidRequest_ShouldReturnOK() throws Exception {

        this.mockMvc.perform(post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(
                                new NewUserRequest("hanna@solo.com", "mIttpw!12", "hanna", 25)

                        )))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        new ObjectMapper().writeValueAsString(
                                new UserInfo(1,"hanna",25, 1)
                        )
                ));


        this.mockMvc.perform(put("/api/update-balance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(
                                new SetBalanceRequest(1,100)
                        )))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        new ObjectMapper().writeValueAsString(
                                new Balance(1, 100, 1)
                        )
                ));
    }

    @Test
    void setBalance_InvalidBalance_ShouldReturnBAD() throws Exception {
        this.mockMvc.perform(put("/api/update-balance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(
                                new SetBalanceRequest(1,-100)
                        )))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(
                        new ObjectMapper().writeValueAsString(
                                new Balance()
                        )
                ));
    }
    @Test
    void getBalance_ValidRequest_ShouldReturnOK() throws Exception {
        Integer balance = 100;
        this.mockMvc.perform(get("/api/balance/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        new ObjectMapper().writeValueAsString(
                                balance
                        )
                ));
    }

    @Test
    void getBalance_InvalidRequest_ShouldReturnBAD() throws Exception {
        Integer balance = 0;
        this.mockMvc.perform(get("/api/balance/6")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(
                        new ObjectMapper().writeValueAsString(
                                balance
                        )
                ));
    }
}