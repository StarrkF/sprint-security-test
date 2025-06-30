package com.example.springsecuritytest.service;

import com.example.springsecuritytest.dto.request.RegisterRequest;
import com.example.springsecuritytest.model.User;
import com.example.springsecuritytest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseCrudService<User, Long, UserRepository> {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
        super(repo);
        this.passwordEncoder = passwordEncoder;
    }

    public boolean existsByUsername(String username) {
        return getRepository().existsByUsername(username);
    }

    public boolean existByEmail(String email) {
        return getRepository().existsByEmail(email);
    }

    public User findByUsername(String username) {
        return getRepository().findByUsername(username).orElse(null);
    }

    public User findByEmail(String email) {
        return getRepository().findByEmail(email).orElse(null);
    }

    public ResponseEntity<?> updateUser(User user, RegisterRequest request) {
        //unique check
        if (request.getUsername() != null && !request.getUsername().equals(user.getUsername())) {
            if (existsByUsername(request.getUsername())) {
                return ResponseEntity.badRequest().body("Username already taken");
            }
            user.setUsername(request.getUsername());
        }

        if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
            if (existByEmail(request.getEmail())) {
                return ResponseEntity.badRequest().body("Email already taken");
            }
            user.setEmail(request.getEmail());
        }

        //password check
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return ResponseEntity.ok(save(user));
    }

}
