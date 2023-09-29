package com.example.teststrategy.controllers;

import com.example.teststrategy.models.Balance;
import com.example.teststrategy.models.Login;
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


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class Controller {
    private final UserInfoRepository userRepo;
    final private BalanceRepository balanceRepo;
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<UserInfo> getUser(@RequestBody LoginRequest loginRequest) {
        if(loginService.authenticate(loginRequest)){
            return new ResponseEntity<>(loginService.login(loginRequest.getEmail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new UserInfo(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<Integer> getBalance(@PathVariable int id) {
        Balance balance = balanceRepo.findByUserinfoId(id);
        if(balance != null){
            return new ResponseEntity<>(balance.getBalance(), HttpStatus.OK);
        }
        return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/create")
    public ResponseEntity<UserInfo> createNewUser(@RequestBody NewUserRequest newUserRequest) {
        if (loginService.validateNewUser(newUserRequest)) {
            return new ResponseEntity<>(loginService.register(newUserRequest), HttpStatus.OK);
        }
        return new ResponseEntity<>(new UserInfo(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update-balance")
    public ResponseEntity<Balance> updateBalance(@RequestParam(value = "-1") int userId, @RequestParam(value = "-1") int balance){
        if(userId != -1 || balance != -1){
            if(userRepo.existsById(userId)){
                Balance currentBalance = balanceRepo.findByUserinfoId(userId);
                currentBalance.setBalance(balance);
                return new ResponseEntity<>(balanceRepo.save(currentBalance), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Balance(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Balance(), HttpStatus.BAD_REQUEST);
    }
}
