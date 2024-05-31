package com.example.ecommerce.cart;

import com.example.ecommerce.cartItem.CartItemDto;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Transactional
public class CartController {
    private final CartService service;
    private final CartRepository cartRepository;
    private final ProductRepository repository;
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<CartResponse> addItemToCart(@RequestBody CartItemDto cartItemDto, Principal user) throws Exception {
        Product product = repository.findByProductId(cartItemDto.getProductId());

        var optionalUser = userService.getCurrentUser(user);


        return ResponseEntity.ok(service.addItemToCart(cartItemDto, product, optionalUser));
    }

    @GetMapping
    public ResponseEntity<?> getCartItemsByUser(Principal principalUser) {
        User user = userService.getCurrentUser(principalUser);
        return ResponseEntity.ok(cartRepository.findByUser(user));
    }

    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<?> removeCartItem(@PathVariable Long cartId) throws Exception {
        service.removeCartItem(cartId);
        return ResponseEntity.ok(cartId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cart>> getAllCarts() {
        return ResponseEntity.ok(service.getAll());
    }

}
