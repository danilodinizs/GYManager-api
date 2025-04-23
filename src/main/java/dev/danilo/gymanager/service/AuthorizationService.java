package dev.danilo.gymanager.service;

import dev.danilo.gymanager.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorizationService implements UserDetailsService {

    private final UserRepository repository;

    public AuthorizationService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Searching for a user by email: {}", username);
        UserDetails byEmail = repository.findByEmail(username);

        if(byEmail == null) {
            log.error("User not found with email: {}", username);
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        return byEmail;
    }
}
