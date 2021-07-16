package com.socialapp.backend.refresh_token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private String token;

    private LocalDateTime expiryDate;
}
