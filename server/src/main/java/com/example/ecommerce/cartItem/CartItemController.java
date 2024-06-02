package com.example.ecommerce.cartItem;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cartItem")
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemRepository cartItemRepository;

    @GetMapping
    public List<CartItem> getAllCartItems () {
        return cartItemRepository.findAll();
    }
}
