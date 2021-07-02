package com.socialapp.backend.refresh_token.repository;

import com.socialapp.backend.refresh_token.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository {
    void insert(RefreshToken refreshToken);

    void deleteById(Long id);

    Optional<RefreshToken> findById(Long id);

    Optional<RefreshToken> findByToken(String token);
}
