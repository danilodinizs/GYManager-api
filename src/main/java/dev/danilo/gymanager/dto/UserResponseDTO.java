package dev.danilo.gymanager.dto;

import lombok.Builder;

@Builder
public record UserResponseDTO(String name,
                              String email,
                              String role) {
}
