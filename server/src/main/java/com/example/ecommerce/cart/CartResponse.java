package com.example.ecommerce.cart;

import com.example.ecommerce.cartItem.CartItem;
import lombok.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    private Long cartId;
    private Set<CartItem> cartItems;
}
