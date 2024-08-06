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
    public ResponseEntity<CartResponse> addItemToCart(@RequestBody CartItemDto cartItemDto, Principal principalUser) throws Exception {
        Optional<Product> product = repository.findById(cartItemDto.getProductId());
        if(product.isEmpty()){
            throw new Exception();
        }

        User user = userService.getCurrentUser(principalUser);


        return ResponseEntity.ok(service.addItemToCart(cartItemDto, product.get(), user));
    }

    @GetMapping
    public ResponseEntity<?> getCartByUser(Principal principalUser) {
        User user = userService.getCurrentUser(principalUser);
        return ResponseEntity.ok(service.getCartByUser(user).getBody());
    }

    @PostMapping("/removeQuantity")
    public ResponseEntity<?> removeQuantity(@RequestBody Long cartItemId, Principal principalUser) {
        User user = userService.getCurrentUser(principalUser);
        service.removeQuantity(cartItemId, user);
        return ResponseEntity.ok(cartItemId);
    }

    @PostMapping("/addQuantity")
    public ResponseEntity<?> addQuantity(@RequestBody Long cartItemId, Principal principalUser) {
        var user = userService.getCurrentUser(principalUser);
        service.addQuantity(cartItemId, user);
        return ResponseEntity.ok(cartItemId);
    }

    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<?> removeCartItem(@PathVariable Long cartItemId, Principal user) throws Exception {
        var optionalUser = userService.getCurrentUser(user);
        service.removeCartItem(cartItemId, optionalUser);
        return ResponseEntity.ok(cartItemId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cart>> getAllCarts() {
        return ResponseEntity.ok(service.getAll());
    }

}
