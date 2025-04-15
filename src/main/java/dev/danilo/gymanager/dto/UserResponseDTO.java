package dev.danilo.gymanager.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserResponseDTO(String name,
                              String email,
                              String role) {
}
