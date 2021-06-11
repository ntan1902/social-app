package com.socialapp.backend.user.dao.impl;

import com.socialapp.backend.user.dao.UserRepository;
import com.socialapp.backend.user.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users u WHERE u.username = ?";
        Object[] params = new Object[]{username};

        User user = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(User.class), params);

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findById(Long userId) {
        String sql = "SELECT * FROM users u WHERE u.id = ?";
        Object[] params = new Object[]{userId};

        User user = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(User.class), params);
//        user.setPassword(null);

        return Optional.ofNullable(user);
    }

    @Override
    public User insertUser(User user) {
//        String sql = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
//
//        jdbcTemplate.update(sql, params);

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        Number key = simpleJdbcInsert
                .withTableName("users")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(params);

        user.setId(key.longValue());
        user.setPassword(null);
        return user;
    }
}
