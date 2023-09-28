package com.example.teststrategy.repositories;

import com.example.teststrategy.models.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance, Integer> {
  Balance findByUserinfoId(int id);
}
