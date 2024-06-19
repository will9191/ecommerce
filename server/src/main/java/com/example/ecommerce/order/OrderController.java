package com.example.ecommerce.order;

import com.example.ecommerce.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
@Transactional
public class OrderController {
    private final OrderService service;
    private final UserService userService;
    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestBody OrderDto orderDto, Principal user) throws Exception {
        var principalUser = userService.getCurrentUser(user);
        service.saveOrder(orderDto, principalUser);

        return ResponseEntity.ok("ok");
    }

    @PostMapping("/test")
    public ResponseEntity<?> testOrder(@RequestBody OrderDto orderDto, Principal user) throws Exception {
        var principalUser = userService.getCurrentUser(user);
//        service.saveOrder(orderDto, principalUser);

        return ResponseEntity.ok(orderDto);
    }

    @GetMapping()
    public ResponseEntity<?> getOrdersByUser(Principal principalUser){
        var user = userService.getCurrentUser(principalUser);
        return ResponseEntity.ok(service.getOrdersByUser(user));
    }
}
