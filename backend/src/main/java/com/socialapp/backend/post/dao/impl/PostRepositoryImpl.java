package com.socialapp.backend.post.dao.impl;

import com.socialapp.backend.post.dao.PostRepository;
import com.socialapp.backend.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Post> insertPost(Post post) {
        String sql = "INSERT INTO posts(`userId`, `description`, `img`) VALUES (?, ?, ?)";
        Object[] params = new Object[]{post.getUserId(), post.getDescription(), post.getImg()};

        this.jdbcTemplate.update(sql, params);
        return Optional.of(post);
    }
}
