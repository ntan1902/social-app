package com.socialapp.backend.friend;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class FriendRepositoryImpl implements FriendRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int insert(Friend friend) {
        String sql = "INSERT INTO friends(`user_id`, `friend_id`) VALUES (:userId, :friendId)";

        SqlParameterSource params = new BeanPropertySqlParameterSource(friend);

        return jdbcTemplate.update(sql, params);
    }

    @Override
    public int delete(Long id, Long friendId) {
        String sql = "DELETE FROM `friends` WHERE user_id = :userId AND friend_id = :friendId";

        Map<String, Object> params = new HashMap<>();
        params.put("userId", id);
        params.put("friendId", friendId);

        return jdbcTemplate.update(sql, params);
    }

    @Override
    public boolean isUserInFriends(Long id, Long friendId) {
        String sql = "SELECT COUNT(*) FROM `friends` WHERE user_id = :userId AND friend_id = :friendId";

        Map<String, Object> params = new HashMap<>();
        params.put("userId", id);
        params.put("friendId", friendId);

        Integer num = jdbcTemplate.queryForObject(sql, params, Integer.class);

        return num != null && num > 0;
    }

    @Override
    public Optional<List<Friend>> findAllByUserId(Long id) {
        String sql = "SELECT * FROM `friends` WHERE user_id = :userId";

        Map<String, Object> params = new HashMap<>();
        params.put("userId", id);

        List<Friend> res = jdbcTemplate.query(sql, params, BeanPropertyRowMapper.newInstance(Friend.class));
        return Optional.of(res);
    }
}

