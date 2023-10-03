package com.example.teststrategy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TestConnectionWithDatabase {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testDatabaseConnection() {
        assertNotNull(dataSource);
    }
}
