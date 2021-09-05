package com.socialapp.backend.refresh_token;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int insert(RefreshToken refreshToken) {
        String sql = "insert into refresh_tokens(user_id, token, expiry_date) values(:userId, :token, :expiryDate)";

        SqlParameterSource params = new BeanPropertySqlParameterSource(refreshToken);

        return jdbcTemplate.update(sql, params);
    }

    @Override
    public int deleteById(Long id) {
        String sql = "delete from refresh_tokens where id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        return jdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<RefreshToken> findById(Long id) {
        String sql = "select * from refresh_tokens where id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        RefreshToken refreshToken = jdbcTemplate.queryForObject(sql, params, BeanPropertyRowMapper.newInstance(RefreshToken.class));
        return Optional.ofNullable(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        String sql = "select * from refresh_tokens where token = :token";

        Map<String, Object> params = new HashMap<>();
        params.put("token", token);

        RefreshToken refreshToken = jdbcTemplate.queryForObject(sql, params, BeanPropertyRowMapper.newInstance(RefreshToken.class));
        return Optional.of(refreshToken);
    }
}
