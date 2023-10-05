package com.example.teststrategy.controllers;

import com.example.teststrategy.models.Balance;
import com.example.teststrategy.models.UserInfo;
import com.example.teststrategy.request.LoginRequest;
import com.example.teststrategy.request.NewUserRequest;
import com.example.teststrategy.request.SetBalanceRequest;
import com.example.teststrategy.services.Shot;
import com.example.teststrategy.services.ValidateAndResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class Controller {
    private final ValidateAndResourceService validateAndResourceService;

    @PostMapping("/login")
    public ResponseEntity<UserInfo> getUser(@RequestBody LoginRequest loginRequest) {
        if(validateAndResourceService.authenticate(loginRequest)){
            return new ResponseEntity<>(validateAndResourceService.login(loginRequest.getEmail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new UserInfo(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<Integer> getBalance(@PathVariable int id) {
        if(validateAndResourceService.balanceExistByUserId(id)){
            return new ResponseEntity<>(validateAndResourceService.getBalance(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/create")
    public ResponseEntity<UserInfo> createNewUser(@RequestBody NewUserRequest newUserRequest) {
        if (validateAndResourceService.validateNewUser(newUserRequest)) {
            return new ResponseEntity<>(validateAndResourceService.register(newUserRequest), HttpStatus.OK);
        }
        return new ResponseEntity<>(new UserInfo(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update-balance")
    public ResponseEntity<Balance> updateBalance(@RequestBody SetBalanceRequest balanceRequest){
        if(validateAndResourceService.validateIntegerBeingPositive(balanceRequest.getBalance()) &&
        validateAndResourceService.balanceExistByUserId(balanceRequest.getUserId())){
            return new ResponseEntity<>(validateAndResourceService.updateBalance(balanceRequest.getUserId(),
                    balanceRequest.getBalance()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Balance(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/shoot")
    public ResponseEntity<String> shoot(@RequestBody Shot shot){
        return new ResponseEntity<>(validateAndResourceService.hitOrMiss(shot), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("Hello world!", HttpStatus.OK);
    }
}
