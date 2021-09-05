package com.socialapp.backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users u WHERE u.username = :username";

        Map<String, Object> params = new HashMap<>();
        params.put("username", username);

        User user = jdbcTemplate.queryForObject(sql, params, BeanPropertyRowMapper.newInstance(User.class));
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users u WHERE u.email = :email";

        Map<String, Object> params = new HashMap<>();
        params.put("email", email);

        User user = jdbcTemplate.queryForObject(sql, params, BeanPropertyRowMapper.newInstance(User.class));
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM users u WHERE u.id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);


        User user = jdbcTemplate.queryForObject(sql, params, BeanPropertyRowMapper.newInstance(User.class));
        return Optional.ofNullable(user);
    }

    @Override
    public int insert(User user) {
        String sql = "INSERT INTO users(username, `password`, email, `profile_picture`, `cover_picture`, `description`, `city`, `from_city`) " +
                "VALUES (:username, :password, :email, :profilePicture, :coverPicture, :description, :city, :fromCity)";

        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        return jdbcTemplate.update(sql, params);
    }

    @Override
    public int update(User user) {
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
        return jdbcTemplate.update(sql, params);
    }

    @Override
    public int deleteById(Long id) {
        String sql = "DELETE FROM `users` WHERE id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        return jdbcTemplate.update(sql, params);
    }

    @Override
    public List<User> findFollowings(Long id) {
        String sql = "SELECT followed_user.* " +
                "FROM users user, `follows` fol, users followed_user " +
                "WHERE user.id = :id AND user.id = fol.user_id AND followed_user.id = fol.following_id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        return jdbcTemplate.query(sql, params, BeanPropertyRowMapper.newInstance(User.class));
    }
}
