package com.example.ecommerce.auth;


import com.example.ecommerce.exceptions.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody @Valid RegisterRequest request
    ) throws Exception {
        try{
            return ResponseEntity.ok(service.register(request));
        } catch (RuntimeException runtimeException) {
            ErrorResponse errorResponse = new ErrorResponse(runtimeException.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }

    }

//        @GetMapping("/profile")
//    public ResponseEntity<User> getUserInfo() {
//       return ResponseEntity.ok(service.getCurrentUser);
//    }
//

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationDto request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
}
