package dev.danilo.gymanager.mapper;

import dev.danilo.gymanager.dto.UserRequestDTO;
import dev.danilo.gymanager.dto.UserResponseDTO;
import dev.danilo.gymanager.entity.User;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toDto(User entity) {
        return UserResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .build();
    }

    public User toEntity(UserRequestDTO dto) {
        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
                .build();
    }
}
