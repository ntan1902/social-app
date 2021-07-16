package com.socialapp.backend.refresh_token;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void insert(RefreshToken refreshToken) {
        String sql = "insert into refresh_tokens(user_id, token, expiry_date) values(:userId, :token, :expiryDate)";

        SqlParameterSource params = new BeanPropertySqlParameterSource(refreshToken);
        NamedParameterJdbcOperations template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, params);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from refresh_tokens where id = ?";

        Object[] params = new Object[]{id};

        jdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<RefreshToken> findById(Long id) {
        String sql = "select * from refresh_tokens where id = ?";

        Object[] params = new Object[]{id};

        RefreshToken refreshToken = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(RefreshToken.class), params);
        return Optional.of(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        String sql = "select * from refresh_tokens where token = ?";
        Object[] params = new Object[]{token};

        RefreshToken refreshToken = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(RefreshToken.class), params);
        return Optional.of(refreshToken);
    }
}
