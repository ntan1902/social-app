package com.socialapp.backend.user_conversation.repository;

import com.socialapp.backend.user_conversation.entity.UserConversation;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class UserConversationRepositoryImpl implements UserConversationRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void insert(UserConversation userConversation) {
        String sql = "INSERT INTO user_conversations(`first_user_id`, `second_user_id`) VALUES (:firstUserId, :secondUserId)";

        SqlParameterSource params = new BeanPropertySqlParameterSource(userConversation);
        NamedParameterJdbcOperations template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, params);
    }

    @Override
    public void delete(Long firstUserId, Long secondUserId) {
        String sql = "DELETE FROM user_conversations WHERE first_user_id = ? AND second_user_id = ?";

        Object[] params = new Object[]{firstUserId, secondUserId};

        this.jdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<List<UserConversation>> findAllByUserId(Long userId) {
        String sql = "SELECT * FROM user_conversations WHERE first_user_id = ?";

        Object[] params = new Object[]{userId};

        List<UserConversation> userConversations = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(UserConversation.class), params);
        return Optional.of(userConversations);
    }
}

