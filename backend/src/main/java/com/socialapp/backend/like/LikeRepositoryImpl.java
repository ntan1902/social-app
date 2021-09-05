package com.socialapp.backend.like;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public boolean isUserLikedPost(Long id, Long userId) {
        String sql = "SELECT COUNT(*) FROM `like_posts` WHERE post_id = :id AND user_id = :userId";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("userId", userId);

        Integer num = jdbcTemplate.queryForObject(sql,  params, Integer.class);

        return num != null && num > 0;
    }

    @Override
    public int insert(Like like) {
        String sql = "INSERT INTO like_posts(`post_id`, `user_id`) VALUES (:postId, :userId)";

        SqlParameterSource params = new BeanPropertySqlParameterSource(like);

        return jdbcTemplate.update(sql, params);
    }

    @Override
    public int delete(Long id, Long userId) {
        String sql = "DELETE FROM `like_posts` WHERE post_id = :id AND user_id = :userId";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("userId", userId);

        return this.jdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<List<Like>> findAllByPostId(Long id) {
        String sql = "SELECT * FROM like_posts li WHERE li.post_id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        List<Like> likes = jdbcTemplate.query(sql, params, BeanPropertyRowMapper.newInstance(Like.class));
        return Optional.of(likes);
    }
}
