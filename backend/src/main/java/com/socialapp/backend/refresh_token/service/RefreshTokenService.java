package com.socialapp.backend.refresh_token.service;

import com.socialapp.backend.refresh_token.entity.RefreshToken;


public interface RefreshTokenService {
    RefreshToken findById(Long id);

    RefreshToken findByToken(String token);

    RefreshToken insert(Long userId);

    RefreshToken validateToken(RefreshToken refreshToken);
}
