package com.socialapp.backend.post.dao.impl;

import com.socialapp.backend.post.dao.PostRepository;
import com.socialapp.backend.post.entity.Post;
import com.socialapp.backend.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
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
    public Optional<Post> insertPost(Post post) {
        String sql = "INSERT INTO posts(`userId`, `description`, `img`) VALUES (:userId, :description, :img)";

        SqlParameterSource params = new BeanPropertySqlParameterSource(post);
        NamedParameterJdbcOperations template = new NamedParameterJdbcTemplate(jdbcTemplate);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int key = template.update(sql, params, keyHolder);
        if (key != 0) {
            return Optional.of(
                    this.findPostById(
                            keyHolder.getKey().longValue()
                    ).get()
            );
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Post> updatePost(Post post) {
        String sql = "UPDATE posts SET description=:description, img=:img WHERE id = :id AND userId=:userId";

        SqlParameterSource params = new BeanPropertySqlParameterSource(post);
        NamedParameterJdbcOperations template = new NamedParameterJdbcTemplate(jdbcTemplate);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int key = template.update(sql, params);
        if (key != 0) {
            return Optional.of(
                    this.findPostById(
                            keyHolder.getKey().longValue()
                    ).get()
            );
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deletePostById(Long id) {
        String sql = "DELETE FROM posts WHERE id=?";
        this.jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Post> findPostById(Long id) {
        String sql = "SELECT * FROM posts WHERE id=?";
        Object[] params = new Object[]{id};

        Post post = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Post.class), params);
        return Optional.ofNullable(post);
    }

    @Override
    public boolean isUserLikedPost(Long id, Long userId) {
        String sql = "SELECT COUNT(*) FROM `like_posts` WHERE postId = ? AND userId = ?";
        Object[] params = new Object[]{id, userId};

        Integer num = jdbcTemplate.queryForObject(sql, Integer.class, params);

        return num != 0;
    }

    @Override
    public void insertLike(Long id, Long userId) {
        String sql = "INSERT INTO like_posts(`postId`, `userId`) VALUES (?, ?)";

        Object[] params = new Object[]{id, userId};

        this.jdbcTemplate.update(sql, params);
    }

    @Override
    public void removeLike(Long id, Long userId) {
        String sql = "DELETE FROM `like_posts` WHERE postId = ? AND userId = ?";
        this.jdbcTemplate.update(sql, id, userId);
    }

    @Override
    public Optional<List<Post>> findPostsByUserId(Long userId) {
        String sql = "SELECT * FROM posts WHERE userId=?";
        Object[] params = new Object[]{userId};

        List<Post> posts = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Post.class), params);
        return Optional.of(posts);
    }

    @Override
    public Optional<List<User>> findLikesOfPost(Long id) {
        String sql = "SELECT u.* FROM like_posts li, users u WHERE li.postId = ? AND li.userId = u.id";
        Object[] params = new Object[]{id};

        List<User> users = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class), params);
        return Optional.of(users);
    }
}
