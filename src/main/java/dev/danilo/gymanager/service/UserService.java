package dev.danilo.gymanager.service;

import dev.danilo.gymanager.dto.UserRequestDTO;
import dev.danilo.gymanager.dto.UserResponseDTO;
import dev.danilo.gymanager.entity.User;
import dev.danilo.gymanager.mapper.UserMapper;
import dev.danilo.gymanager.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public UserResponseDTO save(UserRequestDTO dto) {
        log.info("Saving an User");

        if (repository.existsByEmail(dto.email())) {
            throw new DataIntegrityViolationException("Email already registered: " + dto.email());
        }
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }
    
    public void delete(String email) {
        log.info("Stating the process of deleting an user by email: {}", email);
        UserDetails byEmail = repository.findByEmail(email);

        if(byEmail == null) {
            log.error("User not found with email: {}", email);
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        log.info("User deleted: {}", (User) byEmail);

        repository.delete((User) byEmail);
    }

    public List<UserResponseDTO> findAll() {

        log.info("Looking for all users");
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

}
