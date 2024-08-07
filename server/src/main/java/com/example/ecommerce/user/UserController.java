package com.example.ecommerce.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Transactional
public class UserController {
    private final UserService service;

    @GetMapping("/hello")
    public String response () {
        return "Ok";
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserData(Principal connectedUser) {
        return ResponseEntity.ok(service.getCurrentUser(connectedUser));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(service.findAll());
    }
}
