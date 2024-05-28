package com.example.ecommerce.cart;

import com.example.ecommerce.product.Product;
import com.example.ecommerce.product.ProductDto;
import com.example.ecommerce.product.ProductRepository;
import com.example.ecommerce.product.ProductService;
import com.example.ecommerce.size.Size;
import com.example.ecommerce.user.User;
import com.example.ecommerce.user.UserResponse;
import com.example.ecommerce.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService service;
    private final CartRepository cartRepository;
    private final ProductRepository repository;
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addItemToCart(@RequestBody CartDto cartDto, Principal user) throws Exception {
        Product product = repository.findByProductId(cartDto.getProductId());

        var optionalUser = userService.getCurrentUser(user);

        service.addItemToCart(cartDto, product, optionalUser);
        return ResponseEntity.ok("cartItemDto");
    }

    @GetMapping("/cartItems")
    public ResponseEntity<List<Cart>> getCartItemsByUser(Principal principalUser) {
        User user = userService.getCurrentUser(principalUser);
        return ResponseEntity.ok(cartRepository.findAllByUser(user));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeCartItem(@RequestParam Long cartId) throws Exception {
        service.removeCartItem(cartId);
        return ResponseEntity.ok("removed");
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        return ResponseEntity.ok(service.getAll());
    }

}
