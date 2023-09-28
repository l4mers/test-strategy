package com.example.teststrategy.services;

import com.example.teststrategy.models.Balance;
import com.example.teststrategy.repositories.BalanceRepository;
import com.example.teststrategy.repositories.LoginRepository;
import com.example.teststrategy.repositories.UserInfoRepository;
import com.example.teststrategy.request.LoginRequest;
import com.example.teststrategy.request.NewUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    final private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    LoginRepository loginRepo;
    UserInfoRepository userRepo;
    BalanceRepository balanceRepo;

    public boolean validateNewUser(NewUserRequest newUserRequest){
        return isEmail(newUserRequest.getEmail()) ||
                validatePasswordLength(newUserRequest.getPassword()) ||
                validateCapital(newUserRequest.getPassword()) ||
                validateSymbol(newUserRequest.getPassword()) ||
                validateNameLength(newUserRequest.getName()) ||
                validateAge(newUserRequest.getAge()) ||
                emailExist(newUserRequest.getEmail());
    }

    public boolean isEmail(String email){
        return true;
    }

    public boolean validatePasswordLength(String password){
        return true;
    }

    public boolean validateNameLength(String password){
        return true;
    }

    public boolean validateCapital(String password){
        return true;
    }

    public boolean validateSymbol(String password){
        return true;
    }

    public boolean validateAge(int age){
        return true;
    }

    public boolean emailExist(String email){
        return loginRepo.existsByEmail(email);
    }

    public boolean authenticate(LoginRequest loginRequest){

    }
}
