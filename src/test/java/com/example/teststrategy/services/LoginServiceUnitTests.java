package com.example.teststrategy.services;

import com.example.teststrategy.models.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceUnitTests {

    LoginService validator = new LoginService();

    @Test
    void testThatEmailIsWrittenCorrect() {
        assertTrue(validator.isEmail("hej@hej.se"));
        assertFalse(validator.isEmail("hejhej"));
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

}