package dev.danilo.gymanager.controller;

import dev.danilo.gymanager.dto.UserRequestDTO;
import dev.danilo.gymanager.dto.UserResponseDTO;
import dev.danilo.gymanager.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }
}
