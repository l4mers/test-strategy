package com.example.teststrategy.services;

import com.example.teststrategy.models.Balance;
import com.example.teststrategy.models.Login;
import com.example.teststrategy.models.UserInfo;
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
        if (email == null) {
            return false;
        }

        String emailRegexPattern = "^[A-Za-z0-9+_.-]+@(.+)$";

        return email.matches(emailRegexPattern);
    }


    public boolean validatePasswordLength(String password){
        if (password == null || password.length() <5 || password.length() >30) {
            return false;
        }
        return true;
    }

    public boolean validateNameLength(String name){
        if (name == null || name.length() <2 || name.length() >20) {
            return false;
        }
        return true;
    }

    public boolean validateCapital(String password){
        return password != null && password.matches(".*[A-Z].*");
    }

    public boolean validateSymbol(String password){
        return password != null && password.matches(".*[!@#$%^&*()\\[\\]{};:'\"<>,.?/~`_-].*");
    }

    public boolean validateAge(int age){
        if (age < 18 || age > 120) {
            return false;
        }
        return true;
    }

    public boolean emailExist(String email){
        return !loginRepo.existsByEmail(email);
    }

    public boolean authenticate(LoginRequest loginRequest){
        Login login = loginRepo.findByEmail(loginRequest.getEmail());
        if(login != null){
            return passwordEncoder.matches(loginRequest.getPassword(), loginRequest.getPassword());
        }
        return false;
    }

    public UserInfo register(NewUserRequest newUserRequest) {
        Login login = loginRepo.save(Login.builder()
                .email(newUserRequest.getEmail())
                .password(passwordEncoder.encode(newUserRequest.getPassword()))
                .build());

        UserInfo userInfo = userRepo.save(UserInfo.builder()
                .name(newUserRequest.getName())
                .age(newUserRequest.getAge())
                .loginId(login.getId())
                .build());

        balanceRepo.save(Balance.builder()
                .balance(0)
                .userinfoId(userInfo.getId())
                .build());

        return userInfo;
    }

    public UserInfo login(String email) {
        return userRepo.findUserInfoByLoginId(loginRepo.findByEmail(email).getId());
    }
}
