package com.example.ecommerce.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User getCurrentUser(Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        return User.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .cart(user.getCart())
                .tokens(user.getTokens())
                .role(user.getRole()).build();
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findByUsername(String username){
        return repository.findByEmail(username);
    }


}
