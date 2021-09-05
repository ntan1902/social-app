package com.socialapp.backend.follow;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int insert(Follow follow) {
        String sql = "INSERT INTO follows(`user_id`, `following_id`) VALUES (:userId, :followingId)";

        SqlParameterSource params = new BeanPropertySqlParameterSource(follow);

        return jdbcTemplate.update(sql, params);
    }

    @Override
    public int delete(Long id, Long followingId) {
        String sql = "DELETE FROM `follows` WHERE user_id = :userId AND following_id = :followingId";

        Map<String, Object> params = new HashMap<>();
        params.put("userId", id);
        params.put("followingId", followingId);

        return jdbcTemplate.update(sql, params);
    }

    @Override
    public boolean isUserInFollowings(Long id, Long userId) {
        String sql = "SELECT COUNT(*) FROM `follows` WHERE user_id = :userId AND following_id = :followingId";

        Map<String, Object> params = new HashMap<>();
        params.put("userId", id);
        params.put("followingId", userId);

        Integer num = jdbcTemplate.queryForObject(sql, params, Integer.class);

        return num != null && num > 0;
    }

    @Override
    public Optional<List<Follow>> findAllByUserId(Long id) {
        String sql = "SELECT * FROM `follows` WHERE user_id = :userId";

        Map<String, Object> params = new HashMap<>();
        params.put("userId", id);

        List<Follow> res = jdbcTemplate.query(sql, params, BeanPropertyRowMapper.newInstance(Follow.class));
        return Optional.of(res);
    }


}
