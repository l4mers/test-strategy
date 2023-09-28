package com.example.teststrategy.repositories;

import com.example.teststrategy.models.Login;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRepositoryTest {

    LoginRepository loginValidator;

    @Test
    void testsIfEmailExistsInDatabase() {
            Login user = loginValidator.findByEmail("nonexistent@example.com");
            assertNull(user);
        }
}