package com.socialapp.backend.post;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int insert(Post post) {
        String sql = "INSERT INTO posts(`user_id`, `description`, `img`) VALUES (:userId, :description, :img)";

        SqlParameterSource params = new BeanPropertySqlParameterSource(post);

        return jdbcTemplate.update(sql, params);
    }

    @Override
    public int update(Post post) {
        String sql = "UPDATE posts SET description=:description, img=:img WHERE id = :id AND user_id=:userId";

        SqlParameterSource params = new BeanPropertySqlParameterSource(post);

        return jdbcTemplate.update(sql, params);
    }

    @Override
    public int deleteById(Long id) {
        String sql = "DELETE FROM posts WHERE id=:id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        return this.jdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<Post> findById(Long id) {
        String sql = "SELECT * FROM posts WHERE id=:id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        Post post = jdbcTemplate.queryForObject(sql, params, BeanPropertyRowMapper.newInstance(Post.class));
        return Optional.ofNullable(post);
    }

    @Override
    public Optional<List<Post>> findAllByUserId(Long userId) {
        String sql = "SELECT * FROM posts WHERE user_id=:userId";

        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);

        List<Post> posts = jdbcTemplate.query(sql, params,  BeanPropertyRowMapper.newInstance(Post.class) );
        return Optional.of(posts);
    }

}
