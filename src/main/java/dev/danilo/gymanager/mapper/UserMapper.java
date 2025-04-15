package dev.danilo.gymanager.mapper;

import dev.danilo.gymanager.dto.UserRequestDTO;
import dev.danilo.gymanager.dto.UserResponseDTO;
import dev.danilo.gymanager.entity.User;

import dev.danilo.gymanager.enums.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toDto(User entity) {
        return UserResponseDTO.builder()
                .name(entity.getName())
                .email(entity.getEmail())
                .role(entity.getRole().getRole())
                .build();
    }

    public User toEntity(UserRequestDTO dto) {
        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
                .role(UserRole.valueOf(dto.role()))
                .build();
    }
}
