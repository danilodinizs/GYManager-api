package dev.danilo.gymanager.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserResponseDTO(UUID id,
                              String name,
                              String email) {
}
