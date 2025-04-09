package dev.danilo.gymanager.dto;

public record UserRequestDTO(String name,
                             String email,
                             String password,
                             String role) {
}
