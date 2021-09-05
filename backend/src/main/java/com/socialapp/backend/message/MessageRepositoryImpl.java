package com.socialapp.backend.message;

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class MessageRepositoryImpl implements MessageRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int insert(Message message) {
        String sql = "INSERT INTO user_messages(`sender_id`, `receiver_id`, `content`) VALUES (:senderId, :receiverId, :content)";

        SqlParameterSource params = new BeanPropertySqlParameterSource(message);

        return jdbcTemplate.update(sql, params);

    }

    @Override
    public Optional<Message> findById(Long id) {
        String sql = "SELECT * FROM user_messages WHERE id=:id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        Message message = jdbcTemplate.queryForObject(sql, params, BeanPropertyRowMapper.newInstance(Message.class));
        return Optional.ofNullable(message);
    }

    @Override
    public Optional<List<Message>> findBySenderIdOrReceiverId(Long senderId, Long receiverId) {
        String sql = "SELECT * FROM user_messages WHERE (sender_id = :senderId AND receiver_id = :receiverId) OR (sender_id = :senderId AND receiver_id = :receiverId) ORDER BY created_at ";

        Map<String, Object> params = new HashMap<>();
        params.put("senderId", senderId);
        params.put("receiverId", receiverId);

        List<Message> messages = jdbcTemplate.query(sql, params, BeanPropertyRowMapper.newInstance(Message.class));
        return Optional.of(messages);
    }


}
