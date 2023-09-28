package com.example.teststrategy.repositories;

import com.example.teststrategy.models.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Integer> {
    Boolean existsByEmail(String email);
}
