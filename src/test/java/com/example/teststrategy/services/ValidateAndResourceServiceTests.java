package com.example.teststrategy.services;

import com.example.teststrategy.models.Balance;
import com.example.teststrategy.models.Login;
import com.example.teststrategy.models.UserInfo;
import com.example.teststrategy.repositories.BalanceRepository;
import com.example.teststrategy.repositories.LoginRepository;
import com.example.teststrategy.repositories.UserInfoRepository;
import com.example.teststrategy.request.LoginRequest;
import com.example.teststrategy.request.NewUserRequest;
import com.example.teststrategy.request.SetBalanceRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ValidateAndResourceServiceTests {
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private LoginRepository loginRepository;
    @Mock
    private BalanceRepository balanceRepository;
    @Mock
    private UserInfoRepository userInfoRepository;
    @InjectMocks
    private ValidateAndResourceService validateAndResourceService;

    @BeforeEach
    public void init() {
        UserInfo user1 = new UserInfo(1, "Karin", 67, 7);
        Login login1 = new Login(7, "riktigidiot@dumedu.se", "Smartass!");
        Balance balance1 = new Balance(2, 91234, 1);

        UserInfo user2 = new UserInfo(2, "Herbert", 32, 3);
        Login login2 = new Login(3, "lönnfetsirap@hotmail.se", "Jagebra.");
        Balance balance2 = new Balance(3, 3, 2);


        when(loginRepository.findByEmail("riktigidiot@dumedu.se")).thenReturn(login1);
        when(loginRepository.findByEmail("lönnfetsirap@hotmail.se")).thenReturn(login2);
        when(userInfoRepository.findById(2)).thenReturn(Optional.of(user2));
        when(userInfoRepository.findUserInfoByLoginId(7)).thenReturn(user1);
        when(userInfoRepository.findUserInfoByLoginId(3)).thenReturn(user2);
        when(balanceRepository.findByUserinfoId(1)).thenReturn(balance1);
        when(balanceRepository.findByUserinfoId(2)).thenReturn(balance2);
        when(validateAndResourceService.updateBalance(2, 1500)).thenReturn(balance2);
        when(validateAndResourceService.balanceExistByUserId(1)).thenReturn(true);

    }

    @Test
    public void testAuthenticateWithValidCredentials() {

        String email = "test@example.com";
        String password = "Password!123";
        LoginRequest loginRequest = new LoginRequest(email, password);
        Login login = Login.builder().email(email).password("$2a$10$S1rbsrCMAj738jKMpRXfYu9A5uvzgG0dejWA.jLqDar3qMqmcwyTO").build();

        when(loginRepository.findByEmail(email)).thenReturn(login);
        when(passwordEncoder.matches(loginRequest.getPassword(), login.getPassword())).thenReturn(true);

        boolean isAuthenticated = validateAndResourceService.authenticate(loginRequest);

        assertTrue(isAuthenticated);
    }

    @Test
    public void testAuthenticateWithInvalidCredentials() {
        when(loginRepository.findByEmail(anyString())).thenReturn(null);
        LoginRequest loginRequest = new LoginRequest("nonexistent@example.com", "password");
        boolean result = validateAndResourceService.authenticate(loginRequest);

        assertFalse(result);
    }

    @Test
    void validateNewUser() {
        NewUserRequest newUserRequest = new NewUserRequest("hej@hej.se", "Tjo22!", "Örjan", 46);

        assertEquals("hej@hej.se", newUserRequest.getEmail());
        assertEquals("Tjo22!", newUserRequest.getPassword());
        assertEquals("Örjan", newUserRequest.getName());
        assertEquals(46, newUserRequest.getAge());
    }


    @Test
    void register() {

        NewUserRequest newUserRequest = new NewUserRequest("test@example.com", "Password!123", "mjo", 25);

        when(loginRepository.save(any(Login.class))).thenReturn(Login.builder().id(1).build());
        when(userInfoRepository.save(any(UserInfo.class))).thenReturn(UserInfo.builder().id(3).build());
        when(passwordEncoder.encode(newUserRequest.getPassword())).thenReturn("$2a$10$S1rbsrCMAj738jKMpRXfYu9A5uvzgG0dejWA.jLqDar3qMqmcwyTO");
        when(balanceRepository.save(any(Balance.class))).thenReturn(Balance.builder().id(1).build());

        UserInfo userInfo = validateAndResourceService.register(newUserRequest);

        assertNotNull(userInfo);
        assertEquals(3, userInfo.getId());

    }

    @Test
    void login() {

        UserInfo returnedUserInfo = validateAndResourceService.login("riktigidiot@dumedu.se");

        assertNotNull(returnedUserInfo);
        assertEquals(1, returnedUserInfo.getId());
        assertEquals("Karin", returnedUserInfo.getName());
        assertEquals(7, returnedUserInfo.getLoginId());
        assertEquals(67, returnedUserInfo.getAge());
    }


    @Test
    void balanceExistByUserId() {
        int userId = 1;
        int userId2 = 3;
        assertTrue(validateAndResourceService.balanceExistByUserId(userId));
        assertFalse(validateAndResourceService.balanceExistByUserId(userId2));

    }

    @Test
    void testToUpdateBalance() {
        int userId = 2;
        int newBalance = 1500;

        Balance updatedBalance = validateAndResourceService.updateBalance(userId, newBalance);

       assertNotNull(updatedBalance);
        assertEquals(newBalance, updatedBalance.getBalance());
    }

    @Test
    void testToGetBalanceOfUser() {
        int userId = 1;
        Integer result = validateAndResourceService.getBalance(userId);

        assertEquals(91234, result);
    }
}
