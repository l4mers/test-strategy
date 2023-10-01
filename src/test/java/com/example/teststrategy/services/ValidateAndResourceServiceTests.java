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
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ValidateAndResourceServiceTests {

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

        UserInfo user3 = new UserInfo(3, "Moe", 35, 10);
        Login login3 = new Login(10, "idiot@idiot.se", "jo");
        Balance balance3 = new Balance(4, 3, 3);


        when(loginRepository.findByEmail("riktigidiot@dumedu.se")).thenReturn(login1);
        when(loginRepository.findByEmail("lönnfetsirap@hotmail.se")).thenReturn(login2);
        when(userInfoRepository.findById(2)).thenReturn(Optional.of(user2));
        when(userInfoRepository.findUserInfoByLoginId(7)).thenReturn(user1);
        when(userInfoRepository.findUserInfoByLoginId(3)).thenReturn(user2);
        when(balanceRepository.findByUserinfoId(1)).thenReturn(balance1);
        when(balanceRepository.findByUserinfoId(2)).thenReturn(balance2);
        when(validateAndResourceService.updateBalance(2, 1500)).thenReturn(balance2);
        when(validateAndResourceService.balanceExistByUserId(1)).thenReturn(true);
        when(loginRepository.save(login3)).thenReturn(login3);
        when(userInfoRepository.save(user3)).thenReturn(user3);
        when(balanceRepository.save(balance3)).thenReturn(balance3);

    }

    @Test
    public void testAuthenticateWithValidCredentials() {

        LoginRequest loginRequest = new LoginRequest("riktigidiot@dumedu.se", "Smartass!");
        boolean result = validateAndResourceService.authenticate(loginRequest);

        assertTrue(result);
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
    }


  /*  @Test
    void register() {
        int userId = 3;
        int loginId= 10;

        NewUserRequest newUserRequest = new NewUserRequest()

        assertNotNull(userInfo);
    }

    @Test
    void login() {
     int userId = 2;

      assertEquals(validateAndResourceService.login("lönnfetsirap@hotmail.com").getId(), userId );

    }

   */

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
