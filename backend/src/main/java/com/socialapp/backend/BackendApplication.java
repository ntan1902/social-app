package com.socialapp.backend;

import com.socialapp.backend.user.dao.UserRepository;
import com.socialapp.backend.user.entity.User;
import com.socialapp.backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
//        String username = "ntan1902";
//        String password = "dump password'; DROP DATABASE social_app;#";
//
//        if (userService.loginUncheckInjection(username, password)) {
//            System.out.println("Login check both username and password successfully");
//        } else {
//            System.out.println("Login check both username and password unsuccessfully");
//        }

//        String ntan1902 = "ntan1902";
//        String hashPassword = "123456";
//        if (userService.loginUncheckInjectionHashPassword(ntan1902, hashPassword)) {
//            System.out.println("Login check username successfully");
//        } else {
//            System.out.println("Login check username unsuccessfully");
//        }
    }
}
