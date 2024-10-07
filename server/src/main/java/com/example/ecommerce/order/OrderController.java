package com.example.ecommerce.order;

import com.example.ecommerce.exceptions.ErrorResponse;
import com.example.ecommerce.payment.Payment;
import com.example.ecommerce.payment.PaymentDto;
import com.example.ecommerce.payment.PaymentRepository;
import com.example.ecommerce.product.Product;
import com.example.ecommerce.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    private final UserService userService;
    private final PaymentRepository paymentRepository;

    @PostMapping
    public ResponseEntity<Order> saveOrder(@Valid @RequestBody OrderDto orderDto, Principal user) throws Exception {
        var principalUser = userService.getCurrentUser(user);


        return ResponseEntity.ok(service.saveOrder(orderDto, principalUser));
    }

    @PostMapping("/pay")
    public ResponseEntity<?> payOrder(@RequestBody Long orderId, Principal user) throws Exception {
        try {
            var principalUser = userService.getCurrentUser(user);

            Payment payment = paymentRepository.findByOrderId(orderId);

            return ResponseEntity.ok(service.payOrder(payment, principalUser));
        } catch (RuntimeException runtimeException) {
            ErrorResponse errorResponse = new ErrorResponse(runtimeException.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }

    }

    @PostMapping("/test")
    public ResponseEntity<?> testOrder(@RequestBody OrderDto orderDto, Principal user) throws Exception {
        var principalUser = userService.getCurrentUser(user);
//        service.saveOrder(orderDto, principalUser);

        return ResponseEntity.ok(orderDto);
    }

    @GetMapping("user")
    public ResponseEntity<?> getOrdersByUser(Principal principalUser) {
        var user = userService.getCurrentUser(principalUser);
        return ResponseEntity.ok(service.getOrdersByUser(user));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Order>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
