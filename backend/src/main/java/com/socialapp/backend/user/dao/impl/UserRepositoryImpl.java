package com.socialapp.backend.user.dao.impl;

import com.socialapp.backend.user.dao.UserRepository;
import com.socialapp.backend.user.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
    public Optional<User> loadUserById(Long id) {
        String sql = "SELECT * FROM users u WHERE u.id = ?";
        Object[] params = new Object[]{id};

        try {
            User user = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(User.class), params);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users u WHERE u.username = ?";
        Object[] params = new Object[]{username};

        try {
            User user = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(User.class), params);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM users u WHERE u.id = ?";
        Object[] params = new Object[]{id};

        try {
            User user = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(User.class), params);
            if (user != null) {
                user.setPassword(null);
            }
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> insertUser(User user) {
//        String sql = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
//
//        jdbcTemplate.update(sql, params);

        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        try {
            Number key = simpleJdbcInsert
                    .withTableName("users")
                    .usingGeneratedKeyColumns("id")
                    .executeAndReturnKey(params);
            user.setId(key.longValue());
            user.setPassword(null);
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> updateUser(User user) {
        String sql = "UPDATE users u " +
                "SET " +
                "u.username = :username, " +
                "u.email = :email " +
                "WHERE " +
                "u.id = :id ";

        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        NamedParameterJdbcOperations template = new NamedParameterJdbcTemplate(jdbcTemplate);

        try {
            int key = template.update(sql, params);
            user.setId((long) key);
            return Optional.of(user);

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM users u WHERE u.id = ?";
        jdbcTemplate.update(sql, id);

    }
}
