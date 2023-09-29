package com.example.teststrategy.repositories;

import com.example.teststrategy.models.Login;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LoginRepositoryTest {

    @Mock
    LoginRepository loginValidator;

    @Test
    void testsIfEmailExistsInDatabase() {
            Login user = loginValidator.findByEmail("nonexistent@example.com");
            assertNull(user);
        }
}