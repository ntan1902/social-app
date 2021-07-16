package com.socialapp.backend.friend;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class FriendRepositoryImpl implements FriendRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void insert(Friend friend) {
        String sql = "INSERT INTO friends(`user_id`, `friend_id`) VALUES (:userId, :friendId)";

        SqlParameterSource params = new BeanPropertySqlParameterSource(friend);
        NamedParameterJdbcOperations template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, params);
    }

    @Override
    public void delete(Long id, Long friendId) {
        String sql = "DELETE FROM `friends` WHERE user_id = ? AND friend_id = ?";
        jdbcTemplate.update(sql, id, friendId);
    }

    @Override
    public boolean isUserInFriends(Long id, Long friendId) {
        String sql = "SELECT COUNT(*) FROM `friends` WHERE user_id = ? AND friend_id = ?";
        Object[] params = new Object[]{id, friendId};

        Integer num = jdbcTemplate.queryForObject(sql, Integer.class, params);

        return num != 0;
    }

    @Override
    public Optional<List<Friend>> findAllByUserId(Long id) {
        String sql = "SELECT * FROM `friends` WHERE user_id = ?";
        Object[] params = new Object[]{id};

        List<Friend> res = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Friend.class), params);
        return Optional.of(res);
    }
}

