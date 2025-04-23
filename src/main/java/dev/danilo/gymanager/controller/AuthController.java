package dev.danilo.gymanager.controller;

import dev.danilo.gymanager.dto.LoginResponseDTO;
import dev.danilo.gymanager.dto.UserRequestDTO;
import dev.danilo.gymanager.dto.UserResponseDTO;
import dev.danilo.gymanager.entity.User;
import dev.danilo.gymanager.service.AuthorizationService;
import dev.danilo.gymanager.service.TokenService;
import dev.danilo.gymanager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthorizationService authService;
    private final UserService userService;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, AuthorizationService authService, UserService userService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.userService = userService;
        this.tokenService = tokenService;
    }


    @PostMapping("/register")
    @Operation(description = "Endpoint responsible for register an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully!"),
            @ApiResponse(responseCode = "400", description = "Error creating an user. Please, check the fields and try again!"),
            @ApiResponse(responseCode = "409", description = "Error creating an user. Email already registered!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity register(@RequestBody @Valid UserRequestDTO dto) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        UserRequestDTO newUser = new UserRequestDTO(dto.name(), dto.email(), encryptedPassword, dto.role());
        userService.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    @Operation(description = "Endpoint responsible for logging in a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User logged in successfully!"),
            @ApiResponse(responseCode = "400", description = "Error loggin in an user. Please, check the fields and try again!"),
            @ApiResponse(responseCode = "401", description = "Invalid email or password!"),
            @ApiResponse(responseCode = "403", description = "Account is deactivated"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity login(@RequestBody @Valid UserRequestDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User )auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @DeleteMapping("/delete/{email}")
    @Operation(summary = "Delete a user (ADMIN only)", security = @SecurityRequirement(name = "JWT"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied (only ADMIN can delete users)"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity delete(@PathVariable String email) {
        userService.delete(email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Get all users (ADMIN only)", security = @SecurityRequirement(name = "JWT"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all users"),
            @ApiResponse(responseCode = "403", description = "Access denied (only ADMIN can view all users)"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }
}
