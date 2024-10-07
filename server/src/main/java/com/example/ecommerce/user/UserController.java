package com.example.ecommerce.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addCoins")
    public ResponseEntity<?> addCoins(@RequestBody double quantity, Principal user){
        var principalUser = service.getCurrentUser(user);


        return ResponseEntity.ok(service.addCoins(quantity, principalUser));
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(service.findAll());
    }
}
