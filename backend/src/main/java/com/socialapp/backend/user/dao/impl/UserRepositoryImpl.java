package com.socialapp.backend.user.dao.impl;

import com.socialapp.backend.user.dao.UserRepository;
import com.socialapp.backend.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

        User user = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(User.class), params);
        return Optional.of(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM users u WHERE u.id = ?";
        Object[] params = new Object[]{id};

        User user = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(User.class), params);
        return Optional.of(user);
    }

    @Override
    public Optional<User> insertUser(User user) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        Number key = simpleJdbcInsert
                .withTableName("users")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(params);

        user.setId(key.longValue());
        return Optional.of(user);
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

        int key = template.update(sql, params);
        if (key != 0) {
            user.setId((long) key);
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM `users` WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<User> findFollowings(Long id) {
        String sql = "SELECT followed_user.* " +
                "FROM users user, `follows` fol, users followed_user " +
                "WHERE user.id = ? AND user.id = fol.user_id AND followed_user.id = fol.following_id";
        Object[] params = new Object[]{id};

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class), params);
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

    @Override
    public boolean isUserInFollowings(Long id, Long userId) {
        String sql = "SELECT COUNT(*) FROM `follows` WHERE user_id = ? AND following_id = ?";
        Object[] params = new Object[]{id, userId};

        Integer num = jdbcTemplate.queryForObject(sql, Integer.class, params);

        return num != 0;
    }
}
