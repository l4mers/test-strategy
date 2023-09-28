package com.example.teststrategy.controllers;

import com.example.teststrategy.models.Balance;
import com.example.teststrategy.models.UserInfo;
import com.example.teststrategy.repositories.BalanceRepository;
import com.example.teststrategy.repositories.LoginRepository;
import com.example.teststrategy.repositories.UserInfoRepository;
import com.example.teststrategy.request.LoginRequest;
import com.example.teststrategy.request.NewUserRequest;
import com.example.teststrategy.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class Controller {

    LoginRepository loginRepo;
    UserInfoRepository userRepo;
    BalanceRepository balanceRepo;
    LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<UserInfo> getUser(@RequestBody LoginRequest loginRequest) {
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<Integer> getBalance(@PathVariable int id) {
        Balance balance = balanceRepo.findByUserinfoId(id);
        return new ResponseEntity<>(balance.getBalance(), HttpStatus.OK);
    }

    @PostMapping("/createaccount")
    public ResponseEntity<UserInfo> createNewUser (@RequestBody NewUserRequest newUserRequest) {
        if (loginService.register(newUserRequest)) {
            userRepo.save()
        }
    }
}
