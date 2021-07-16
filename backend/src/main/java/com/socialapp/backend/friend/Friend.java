package com.socialapp.backend.friend;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Friend implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long friendId;
}

