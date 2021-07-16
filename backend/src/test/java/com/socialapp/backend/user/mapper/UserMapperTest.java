package com.socialapp.backend.user.mapper;

import com.socialapp.backend.user.UserDTO;
import com.socialapp.backend.user.User;
import com.socialapp.backend.user.UserMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserMapperTest {

    @Test
    void Entity2DTO() {
        // Given
        User user = User.builder()
                .id(1L)
                .email("ntan1902@gmail.com")
                .username("ntan1902")
                .build();

        UserMapper underTest = Mappers.getMapper(UserMapper.class);

        // When
        UserDTO userDTO = underTest.map(user);

        // Then
        assertThat(userDTO.getUsername()).isEqualTo(user.getUsername());
        assertThat(userDTO.getEmail()).isEqualTo(user.getEmail());
        assertThat(userDTO.getId()).isEqualTo(user.getId());
    }

    @Test
    void DTO2Entity() {
        // Given
        UserDTO userDTO = UserDTO.builder()
                .id(1)
                .email("ntan1902@gmail.com")
                .username("ntan1902")
                .build();

        UserMapper underTest = Mappers.getMapper(UserMapper.class);

        // When
        User user = underTest.map(userDTO);

        // Then
        assertThat(user.getUsername()).isEqualTo(userDTO.getUsername());
        assertThat(user.getEmail()).isEqualTo(userDTO.getEmail());
        assertThat(user.getId()).isEqualTo(userDTO.getId());
    }
}