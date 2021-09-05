package com.socialapp.backend.refresh_token;

import java.util.Optional;

public interface RefreshTokenRepository {
    int insert(RefreshToken refreshToken);

    int deleteById(Long id);

    Optional<RefreshToken> findById(Long id);

    Optional<RefreshToken> findByToken(String token);
}
