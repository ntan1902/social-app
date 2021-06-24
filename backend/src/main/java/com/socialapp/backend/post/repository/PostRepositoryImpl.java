package com.socialapp.backend.post.repository;

import com.socialapp.backend.post.entity.Post;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Post> insert(Post post) {
        String sql = "INSERT INTO posts(`user_id`, `description`, `img`) VALUES (:userId, :description, :img)";

        SqlParameterSource params = new BeanPropertySqlParameterSource(post);
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
    public Optional<Post> update(Post post) {
        String sql = "UPDATE posts SET description=:description, img=:img WHERE id = :id AND user_id=:userId";

        SqlParameterSource params = new BeanPropertySqlParameterSource(post);
        NamedParameterJdbcOperations template = new NamedParameterJdbcTemplate(jdbcTemplate);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int key = template.update(sql, params);
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
    public void deleteById(Long id) {
        String sql = "DELETE FROM posts WHERE id=?";
        this.jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Post> findById(Long id) {
        String sql = "SELECT * FROM posts WHERE id=?";
        Object[] params = new Object[]{id};

        Post post = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Post.class), params);
        return Optional.ofNullable(post);
    }

    @Override
    public Optional<List<Post>> findAllByUserId(Long userId) {
        String sql = "SELECT * FROM posts WHERE user_id=?";
        Object[] params = new Object[]{userId};

        List<Post> posts = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Post.class), params);
        return Optional.of(posts);
    }

}
