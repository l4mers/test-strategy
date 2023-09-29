package com.example.teststrategy.controllers;

import com.example.teststrategy.request.NewUserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createNewUser_ValidRequest_ShouldReturnOK() throws Exception {
//        NewUserRequest newUserRequest = new NewUserRequest("test@example.com", "password123", "John Doe", 25);
//        String requestBody = objectMapper.writeValueAsString(newUserRequest);
//
//        ResultActions result = mockMvc.perform(post("/create")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestBody))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").isNumber())
//                .andExpect(jsonPath("$.name").value("John Doe"))
//                .andExpect(jsonPath("$.age").value(25));

//        NewUserRequest newUserRequest = new NewUserRequest("han@solo.com", "m");
//        String email;
//        String password;
//        String name;
//        int age;
//
//
//        this.mockMvc.perform(get("/order/all"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(
//                        new ObjectMapper().writeValueAsString(
//
//                        )
//                ));

        NewUserRequest newUserRequest = new NewUserRequest("test@example.com", "Password12!", "John Doe", 25);
        String requestBody = objectMapper.writeValueAsString(newUserRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }
    @Test
    void createNewUser_InvalidRequest_ShouldReturnBAD() throws Exception {

        NewUserRequest newUserRequest = new NewUserRequest("nonono", "as", "Greger Peterson", 25);
        String requestBody = objectMapper.writeValueAsString(newUserRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
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