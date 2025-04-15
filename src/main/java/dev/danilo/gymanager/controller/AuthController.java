package dev.danilo.gymanager.controller;

import dev.danilo.gymanager.dto.UserRequestDTO;
import dev.danilo.gymanager.dto.UserResponseDTO;
import dev.danilo.gymanager.entity.User;
import dev.danilo.gymanager.mapper.UserMapper;
import dev.danilo.gymanager.repository.UserRepository;
import dev.danilo.gymanager.service.AuthorizationService;
import dev.danilo.gymanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthorizationService authService;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, AuthorizationService authService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.userService = userService;

    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserRequestDTO dto) {
        if (authService.loadUserByUsername(dto.email()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        UserRequestDTO newUser = new UserRequestDTO(dto.name(), dto.email(), encryptedPassword, dto.role());
        userService.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserRequestDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity delete(@PathVariable String email) {
        userService.delete(email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }
}
