package com.socialapp.backend.refresh_token.service;

import com.socialapp.backend.exception.user.ApiResponseException;
import com.socialapp.backend.refresh_token.entity.RefreshToken;
import com.socialapp.backend.refresh_token.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService{
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${spring.app.refreshExpirationMs}")
    private long REFRESH_EXPIRATION;

    @Autowired
    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public RefreshToken findById(Long id) {
        return refreshTokenRepository.findById(id)
                .orElseThrow(() -> new ApiResponseException("Invalid refresh token id"));
    }

    @Override
    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new ApiResponseException("Invalid refresh token"));
    }

    @Override
    public RefreshToken insert(Long userId) {
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(userId)
                .token(UUID.randomUUID().toString())
                .expiryDate(LocalDateTime.now().plusSeconds(REFRESH_EXPIRATION / 1000))
                .build();

        refreshTokenRepository.insert(refreshToken);

        return refreshToken;
    }

    @Override
    public RefreshToken validateToken(RefreshToken refreshToken) {
        if(refreshToken.getExpiryDate().compareTo(LocalDateTime.now()) < 0) {
            refreshTokenRepository.deleteById(refreshToken.getId());
            throw new ApiResponseException("Refresh token was expired. Please make a new signin request");
        }
        return refreshToken;
    }


}
