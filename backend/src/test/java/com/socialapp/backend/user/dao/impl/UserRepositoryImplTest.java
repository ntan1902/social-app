package com.socialapp.backend.user.dao.impl;

import com.socialapp.backend.user.dao.UserRepository;
import com.socialapp.backend.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DataJdbcTest
@Import({UserRepositoryImpl.class})
class UserRepositoryImplTest {
    @Configuration
    @EnableAutoConfiguration
    static class Config {
    }

    @Autowired
    private UserRepository underTest;

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

        // then
        assertThat(user.get().getUsername()).isEqualTo("test");
        assertThatThrownBy(() -> underTest.findByUsername("test2"))
                .isInstanceOf(DataAccessException.class);
    }

    @Test
    void test_findById() {
        // given

        // when
        Optional<User> user = underTest.findById(1L);

        // then
        assertThat(user.get().getUsername()).isEqualTo("ntan1902");
        assertThatThrownBy(() -> underTest.findById(100L))
                .isInstanceOf(DataAccessException.class);
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

    @Test
    void test_deleteUser() {
        // given
        Optional<User> optionalUser = underTest.findById(1L);

        // when
        underTest.deleteUserById(1L);

        // then
        assertThatThrownBy(() -> underTest.findById(1L))
                .isInstanceOf(DataAccessException.class);

        // insert
//        underTest.insertUser(optionalUser.get());
    }

    @Test
    void test_findFollowings(){
        // given

        // when
        List<User> followings = underTest.findFollowings(1L);

        // then
        assertThat(followings.get(0).getId()).isEqualTo(2L);
        assertThat(followings.get(1).getId()).isEqualTo(4L);
        assertThat(followings.get(2).getId()).isEqualTo(5L);

    }

}