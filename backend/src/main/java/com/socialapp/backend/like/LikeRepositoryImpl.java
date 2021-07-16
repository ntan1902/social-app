package com.socialapp.backend.like;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean isUserLikedPost(Long id, Long userId) {
        String sql = "SELECT COUNT(*) FROM `like_posts` WHERE post_id = ? AND user_id = ?";
        Object[] params = new Object[]{id, userId};

        Integer num = jdbcTemplate.queryForObject(sql, Integer.class, params);

        return num != 0;
    }

    @Override
    public void insert(Like like) {
        String sql = "INSERT INTO like_posts(`post_id`, `user_id`) VALUES (:postId, :userId)";

        SqlParameterSource params = new BeanPropertySqlParameterSource(like);
        NamedParameterJdbcOperations template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, params);
    }

    @Override
    public void delete(Long id, Long userId) {
        String sql = "DELETE FROM `like_posts` WHERE post_id = ? AND user_id = ?";
        this.jdbcTemplate.update(sql, id, userId);
    }

    @Override
    public Optional<List<Like>> findAllByPostId(Long id) {
        String sql = "SELECT * FROM like_posts li WHERE li.post_id = ?";
        Object[] params = new Object[]{id};

        List<Like> likes = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Like.class), params);
        return Optional.of(likes);
    }
}
