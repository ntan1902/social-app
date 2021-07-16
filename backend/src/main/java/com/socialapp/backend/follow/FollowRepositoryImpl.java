package com.socialapp.backend.follow;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void insert(Follow follow) {
        String sql = "INSERT INTO follows(`user_id`, `following_id`) VALUES (:userId, :followingId)";

        SqlParameterSource params = new BeanPropertySqlParameterSource(follow);
        NamedParameterJdbcOperations template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, params);
    }

    @Override
    public void delete(Long id, Long followingId) {
        String sql = "DELETE FROM `follows` WHERE user_id = ? AND following_id = ?";
        jdbcTemplate.update(sql, id, followingId);
    }

    @Override
    public boolean isUserInFollowings(Long id, Long userId) {
        String sql = "SELECT COUNT(*) FROM `follows` WHERE user_id = ? AND following_id = ?";
        Object[] params = new Object[]{id, userId};

        Integer num = jdbcTemplate.queryForObject(sql, Integer.class, params);

        return num != 0;
    }

    @Override
    public Optional<List<Follow>> findAllByUserId(Long id) {
        String sql = "SELECT * FROM `follows` WHERE user_id = ?";
        Object[] params = new Object[]{id};

        List<Follow> res = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Follow.class), params);

        return Optional.of(res);
    }


}
