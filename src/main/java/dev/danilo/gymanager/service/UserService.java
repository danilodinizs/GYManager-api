package dev.danilo.gymanager.service;

import dev.danilo.gymanager.dto.UserRequestDTO;
import dev.danilo.gymanager.dto.UserResponseDTO;
import dev.danilo.gymanager.entity.User;
import dev.danilo.gymanager.mapper.UserMapper;
import dev.danilo.gymanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public UserResponseDTO save(UserRequestDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }
    
    public void delete(UUID id) {
        Optional<User> byId = repository.findById(id);

        byId.ifPresent(repository::delete);
    }

}
