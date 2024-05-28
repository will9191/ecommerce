package com.example.ecommerce.order;

import com.example.ecommerce.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    private final UserService userService;
    @PostMapping
    public ResponseEntity<?> saveOrder(OrderDto orderDto, Principal user) throws Exception {
        var principalUser = userService.getCurrentUser(user);
        service.saveOrder(orderDto, principalUser);

        return ResponseEntity.ok("ok");
    }
}
