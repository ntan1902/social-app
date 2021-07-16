package com.socialapp.backend.refresh_token;


public interface RefreshTokenService {
    RefreshToken findById(Long id);

    RefreshToken findByToken(String token);

    RefreshToken insert(Long userId);

    RefreshToken validateToken(RefreshToken refreshToken);
}
