package com.example.teststrategy;

import com.example.teststrategy.models.Balance;
import com.example.teststrategy.models.Login;
import com.example.teststrategy.models.UserInfo;
import com.example.teststrategy.repositories.BalanceRepository;
import com.example.teststrategy.repositories.LoginRepository;
import com.example.teststrategy.repositories.UserInfoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TestStrategyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestStrategyApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner init(BalanceRepository balanceRepo, LoginRepository loginRepository, UserInfoRepository userInfoRepository){
//        return (args) -> {
//            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//            Login login = loginRepository.save(Login.builder()
//                    .email("dummy@test.com")
//                    .password(passwordEncoder.encode("TestPassword!"))
//                    .build());
//
//            UserInfo userInfo = userInfoRepository.save(UserInfo.builder()
//                    .name("Dummy Test")
//                    .age(18)
//                    .loginId(login.getId())
//                    .build());
//
//            balanceRepo.save(Balance.builder()
//                    .balance(100)
//                    .userinfoId(userInfo.getId())
//                    .build());
//
//        };
//    }
}
