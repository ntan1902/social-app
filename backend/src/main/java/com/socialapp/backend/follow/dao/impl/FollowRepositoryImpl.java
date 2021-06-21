package com.socialapp.backend.follow.dao.impl;

import com.socialapp.backend.follow.dao.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void insertFollow(Long id, Long followingId) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", id)
                .addValue("followingId", followingId);

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        simpleJdbcInsert
                .withTableName("follows")
                .execute(params);
    }

    @Override
    public void removeFollow(Long id, Long followingId) {
        String sql = "DELETE FROM `follows` WHERE userId = ? AND followingId = ?";
        jdbcTemplate.update(sql, id, followingId);
    }

    @Override
    public boolean isUserInFollowings(Long id, Long userId) {
        String sql = "SELECT COUNT(*) FROM `follows` WHERE userId = ? AND followingId = ?";
        Object[] params = new Object[]{id, userId};

        Integer num = jdbcTemplate.queryForObject(sql, Integer.class, params);

        return num != 0;
    }
}
