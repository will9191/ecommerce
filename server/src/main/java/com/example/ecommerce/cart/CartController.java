package com.example.ecommerce.cart;

import com.example.ecommerce.cartItem.CartItemDto;
import com.example.ecommerce.exceptions.ErrorResponse;
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
public class CartController {
    private final CartService service;
    private final CartRepository cartRepository;
    private final ProductRepository repository;
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addItemToCart(@RequestBody CartItemDto cartItemDto, Principal principalUser) throws Exception {
        try {
            Optional<Product> product = repository.findById(cartItemDto.getProductId());
            if (product.isEmpty()) {
                throw new RuntimeException("Product not found");
            }

            User user = userService.getCurrentUser(principalUser);
            return ResponseEntity.ok(service.addItemToCart(cartItemDto, product.get(), user));
        } catch (RuntimeException runtimeException) {
            ErrorResponse errorResponse = new ErrorResponse(runtimeException.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping
    public ResponseEntity<?> getCartByUser(Principal principalUser) {
        User user = userService.getCurrentUser(principalUser);
        return ResponseEntity.ok(service.getCartByUser(user).getBody());
    }

    @PostMapping("/removeQuantity")
    public ResponseEntity<?> removeQuantity(@RequestBody Long cartItemId, Principal principalUser)throws Exception {
        try {
            User user = userService.getCurrentUser(principalUser);
            return ResponseEntity.ok(service.removeQuantity(cartItemId, user));
        } catch (RuntimeException runtimeException) {
            ErrorResponse errorResponse = new ErrorResponse(runtimeException.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/addQuantity")
    public ResponseEntity<?> addQuantity(@RequestBody Long cartItemId, Principal principalUser)throws Exception {
        try {
            var user = userService.getCurrentUser(principalUser);

            return ResponseEntity.ok(service.addQuantity(cartItemId, user));
        } catch (RuntimeException runtimeException) {
            ErrorResponse errorResponse = new ErrorResponse(runtimeException.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<?> removeCartItem(@PathVariable Long cartItemId, Principal user) throws Exception {
        try {
            var optionalUser = userService.getCurrentUser(user);

            return ResponseEntity.ok(service.removeCartItem(cartItemId, optionalUser));
        } catch (RuntimeException runtimeException) {
            ErrorResponse errorResponse = new ErrorResponse(runtimeException.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cart>> getAllCarts() {
        return ResponseEntity.ok(service.getAll());
    }

}
