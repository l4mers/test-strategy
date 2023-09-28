package com.example.teststrategy.repositories;

import com.example.teststrategy.models.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
}
