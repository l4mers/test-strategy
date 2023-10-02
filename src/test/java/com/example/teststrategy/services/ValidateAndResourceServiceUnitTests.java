package com.example.teststrategy.services;

import com.example.teststrategy.repositories.BalanceRepository;
import com.example.teststrategy.repositories.LoginRepository;
import com.example.teststrategy.repositories.UserInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ValidateAndResourceServiceUnitTests {
    @MockBean
    private LoginRepository loginRepository;
    @MockBean
    private UserInfoRepository userInfoRepository;
    @MockBean
    private BalanceRepository balanceRepository;
    ValidateAndResourceService validator;
    @BeforeEach
    void setUp() {
        validator = new ValidateAndResourceService(loginRepository, userInfoRepository, balanceRepository);
    }

    @Test
    void testThatEmailIsWrittenCorrect() {
        assertTrue(validator.isEmail("hej@hej.se"));
        assertFalse(validator.isEmail("hejhej"));
    }
    @Test
    void testThatEmailIsNull() {
        assertFalse(validator.isEmail(null));
    }

    @Test
    void testsThatPasswordIsNotTooShortOrTooLong(){
        assertTrue(validator.validatePasswordLength("Plånboken2"));
        assertFalse(validator.validatePasswordLength("hejhejehjehjehejehehjeehejehjejhhejhejhejhejehjehjehjhhejhhjehejehejeh"));
        assertFalse(validator.validatePasswordLength(" "));
        assertFalse(validator.validatePasswordLength("i"));
        assertFalse(validator.validatePasswordLength(null));
    }

    @Test
    void testIfNameIsWrittenCorrectly() {
        assertTrue(validator.validateNameLength("Märta"));
        assertFalse(validator.validateNameLength("hejehjehjhjehrjhjhejkahejaotberbgskrntksjrnsysrsrlnls"));
        assertFalse(validator.validateNameLength(" "));
        assertFalse(validator.validateNameLength("M"));
        assertFalse(validator.validateNameLength(null));
    }

    @Test
    void testIfPasswordContainsACapitalLetter() {
        assertFalse(validator.validateCapital("hejsan"));
        assertTrue(validator.validateCapital("Kossa"));
    }

    @Test
    void testsThatPasswordContainsASymbol() {
        assertTrue(validator.validateSymbol("Tjena!"));
        assertFalse(validator.validateSymbol("Tjena"));
        assertFalse(validator.validateSymbol(null));
    }

    @Test
    void testsThatAgeIsNotUnderageOrImpossible() {
        assertTrue(validator.validateAge(90));
        assertFalse(validator.validateAge(-200));
        assertFalse(validator.validateAge(134));
        assertFalse(validator.validateAge(16));
    }

        @Test
        void testifAnIntegerBeingPositive() {
        assertTrue(validator.validateIntegerBeingPositive(0));
        assertTrue(validator.validateIntegerBeingPositive(2163));
        assertFalse(validator.validateIntegerBeingPositive(-2));
        }


}