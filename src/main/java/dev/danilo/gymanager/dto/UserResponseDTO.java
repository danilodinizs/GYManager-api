package dev.danilo.gymanager.dto;

import java.util.UUID;

public record UserResponseDTO(UUID id,
                              String name,
                              String email) {
}
