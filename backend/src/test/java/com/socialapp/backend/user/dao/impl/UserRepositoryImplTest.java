package com.socialapp.backend.user.dao.impl;

import com.socialapp.backend.user.dao.UserRepository;
import com.socialapp.backend.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
class UserRepositoryImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Autowired
    private UserRepository underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserRepositoryImpl(jdbcTemplate);
    }

    @Test
    void test_findByUsername() {
        // given
        underTest.insertUser(
                User.builder()
                        .username("test")
                        .email("test@vng.com.vn")
                        .build()
        );

        // when
        Optional<User> user = underTest.findByUsername("test");
        Optional<User> empty = underTest.findByUsername("test1");

        // then
        assertThat(user.get().getUsername()).isEqualTo("test");
        assertThat(empty).isEmpty();
    }

    @Test
    void test_findById() {
        // given

        // when
        Optional<User> user = underTest.findById(1L);
        Optional<User> empty = underTest.findById(100L);

        // then
        assertThat(user.get().getUsername()).isEqualTo("ntan1902");
        assertThat(empty).isEmpty();
    }

    @Test
    void test_insertUser() {
        // given
        User user = User.builder().username("annt12").email("annt12@vng.com.vn").build();

        // when
        Optional<User> optionalUser = underTest.insertUser(user);

        // then
        assertThat(optionalUser).contains(user);

    }


    @Test
    void test_updateUser() {
        // given
        User user = User.builder().id(1L).username("annt12").email("annt12@vng.com.vn").build();
        User user2 = User.builder().id(8L).username("annt12").email("annt12@vng.com.vn").build();

        // when
        Optional<User> optionalUser = underTest.updateUser(user);

        Optional<User> empty = underTest.updateUser(user2);

        // then
        assertThat(optionalUser.get().getUsername()).isEqualTo("annt12");
        assertThat(empty).isEmpty();
    }
}