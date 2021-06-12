package com.socialapp.backend.user.dao.impl;

import com.socialapp.backend.user.dao.UserRepository;
import com.socialapp.backend.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
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
            simpleJdbcInsert
                    .withTableName("users")
                    .usingGeneratedKeyColumns("id")
                    .executeAndReturnKey(params);
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

    @Override
    public List<User> findFollowings(Long id) {
        String sql = "SELECT followed_user.* " +
                "FROM users user, `follows` fol, users followed_user " +
                "WHERE user.id = ? AND user.id = fol.user_id AND followed_user.id = fol.following_id";
        Object[] params = new Object[]{id};

        try {
            return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class), params);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public void insertFollow(Long id, Long userId) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("user_id", id)
                .addValue("following_id", userId);

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        simpleJdbcInsert
                .withTableName("follows")
                .execute(params);
    }

    @Override
    public void removeFollow(Long id, Long userId) {
        String sql = "DELETE FROM `follows` WHERE user_id = ? AND following_id = ?";
        jdbcTemplate.update(sql, id, userId);
    }
}
