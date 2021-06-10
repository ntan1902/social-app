package com.socialapp.backend;

import com.socialapp.backend.user.entity.User;
import com.socialapp.backend.user.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.Collections;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        User user1 = User.builder()
                .username("ntan1902")
                .email("trinh.an.1902@gmail.com")
                .password(passwordEncoder.encode("123456"))
                .build();

        User user2 = User.builder()
                .username("ohnguyen")
                .email("ohnguyen@kms.com.vn")
                .password(passwordEncoder.encode("123456"))
                .build();

//        user1.setFollowers(Collections.singleton(user2));
//		user2.setFollowings(Collections.singleton(user1));

        userRepository.save(user1);
        userRepository.save(user2);
//		System.out.println(user1);
//		System.out.println(user2);

//        User resultQuery = userRepository.findById(1L).get();
//		System.out.println(resultQuery);
    }
}
