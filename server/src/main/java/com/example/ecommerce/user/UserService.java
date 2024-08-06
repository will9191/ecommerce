package com.example.ecommerce.user;

import com.example.ecommerce.auditing.ApplicationAuditAware;
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
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .cart(user.getCart())
                .coins(user.getCoins())
                .orders(user.getOrders())
                .tokens(user.getTokens())
                .role(user.getRole()).build();
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        return repository.findByEmail(username);
    }


}
