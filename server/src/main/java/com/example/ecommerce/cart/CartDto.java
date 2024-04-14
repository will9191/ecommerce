package com.example.ecommerce.cart;

import com.example.ecommerce.cartItem.CartItemDto;
import com.example.ecommerce.product.ProductDto;
import com.example.ecommerce.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private Long cartId;
    private User user;
    private Set<CartItemDto> cartItems;
    private double totalPrice;
}
