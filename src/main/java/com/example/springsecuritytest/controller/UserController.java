package com.example.springsecuritytest.controller;

import com.example.springsecuritytest.model.User;
import com.example.springsecuritytest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUser() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@RequestParam Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestParam Long id, @RequestBody User request) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        //unique control
        if (request.getUsername() != null && !request.getUsername().equals(user.getUsername())) {
            if (userService.existsByUsername(request.getUsername())) {
                return ResponseEntity.badRequest().body("Username already taken");
            }
            user.setUsername(request.getUsername());
        }
        if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
            if (userService.existByEmail(request.getEmail())) {
                return ResponseEntity.badRequest().body("Email already taken");
            }
            user.setEmail(request.getEmail());
        }

        //password check
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return ResponseEntity.ok(userService.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@RequestParam Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
