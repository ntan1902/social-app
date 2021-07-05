package com.socialapp.backend.message.repository;

import com.socialapp.backend.message.entity.Message;
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
public class MessageRepositoryImpl implements MessageRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Message> insert(Message message) {
        String sql = "INSERT INTO user_messages(`sender_id`, `receiver_id`, `content`) VALUES (:senderId, :receiverId, :content)";

        SqlParameterSource params = new BeanPropertySqlParameterSource(message);
        NamedParameterJdbcOperations template = new NamedParameterJdbcTemplate(jdbcTemplate);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int key = template.update(sql, params, keyHolder);
        if (key != 0) {
            return Optional.of(findById(
                    keyHolder.getKey().longValue()
            ).get());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Message> findById(Long id) {
        String sql = "SELECT * FROM user_messages WHERE id=?";

        Object[] params = new Object[]{id};

        Message message = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Message.class), params);
        return Optional.ofNullable(message);
    }

    @Override
    public Optional<List<Message>> findBySenderIdOrReceiverId(Long senderId, Long receiverId) {
        String sql = "SELECT * FROM user_messages WHERE (sender_id = ? AND receiver_id = ?) OR (sender_id = ? AND receiver_id = ?)";

        Object[] params = new Object[]{senderId, receiverId, receiverId, senderId};

        List<Message> messages = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Message.class), params);
        return Optional.of(messages);
    }


}
