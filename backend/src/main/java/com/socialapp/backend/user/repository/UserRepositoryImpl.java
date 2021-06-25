package com.socialapp.backend.user.repository;

import com.socialapp.backend.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users u WHERE u.email = ?";
        Object[] params = new Object[]{email};

        User user = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(User.class), params);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM users u WHERE u.id = ?";
        Object[] params = new Object[]{id};

        User user = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(User.class), params);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> insert(User user) {
        String sql = "INSERT INTO users(username, `password`, email, `profile_picture`, `cover_picture`, `description`, `city`, `from_city`) " +
                "VALUES (:username, :password, :email, :profilePicture, :coverPicture, :description, :city, :fromCity)";

        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        NamedParameterJdbcOperations template = new NamedParameterJdbcTemplate(jdbcTemplate);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int key = template.update(sql, params, keyHolder);
        if (key != 0) {
            return Optional.of(
                    this.findById(
                            keyHolder.getKey().longValue()
                    ).get()
            );
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> update(User user) {
        String sql = "UPDATE users u " +
                "SET " +
                "u.username = :username, " +
                "u.email = :email, " +
                "u.profile_picture = :profilePicture, " +
                "u.cover_picture = :coverPicture, " +
                "u.description = :description, " +
                "u.city = :city, " +
                "u.from_city = :fromCity " +
                "WHERE " +
                "u.id = :id ";

        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        NamedParameterJdbcOperations template = new NamedParameterJdbcTemplate(jdbcTemplate);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int key = template.update(sql, params, keyHolder);
        if (key != 0) {
            user.setId(keyHolder.getKey().longValue());
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
    public Optional<User> findUserUncheckInjection(String username, String password) {

        String sql = "SELECT * " +
                "FROM `users` " +
                "WHERE `username`='" + username + "' " +
                "AND `password` = '" + password + "'";
        return Optional.ofNullable(
                jdbcTemplate.query(sql, rs -> {
                    if (rs.next()) {
                        return User.builder()
                                .id(rs.getLong("id"))
                                .username(rs.getString("username"))
                                .email(rs.getString("email"))
                                .build();
                    }
                    return null;
                })
        );

    }

    @Override
    public Optional<User> findByUsernameUncheckInjection(String username) {
        String sql = "SELECT * " +
                "FROM `users` " +
                "WHERE `username`='" + username + "'";
        return Optional.ofNullable(
                jdbcTemplate.query(sql, rs -> {
                    if (rs.next()) {
                        return User.builder()
                                .id(rs.getLong("id"))
                                .username(rs.getString("username"))
                                .email(rs.getString("email"))
                                .password(rs.getString("password"))
                                .build();
                    }
                    return null;
                })
        );
    }
}
