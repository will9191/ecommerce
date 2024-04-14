package com.example.ecommerce.cart;

import com.example.ecommerce.cartItem.CartItem;
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
    private final ProductRepository repository;
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addItemToCart(@RequestBody CartItemDto cartItemDto, Principal user) throws Exception {
        Product product = repository.findByProductId(cartItemDto.getProductId());

        var optionalUser = userService.getCurrentUser(user);


        service.addItemToCart(cartItemDto, product, optionalUser);
        return ResponseEntity.ok("cartItemDto");
    }

//    @GetMapping("/public/users/{emailId}/carts/{cartId}")
//    public ResponseEntity<CartDto> getCartById(@PathVariable String emailId, @PathVariable Long cartId) {
//        CartDto cartDto = cartService.getCart(emailId, cartId);
//
//        return new ResponseEntity<CartDto>(cartDto, HttpStatus.OK);
//    }

    @GetMapping("/cartItem")
    public ResponseEntity<List<CartItem>> getAllCartItems(){
        return ResponseEntity.ok(service.getCartItem());
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        return ResponseEntity.ok(service.getAll());
    }

}
