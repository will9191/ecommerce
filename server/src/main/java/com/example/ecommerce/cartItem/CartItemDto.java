package com.example.ecommerce.cartItem;

import com.example.ecommerce.product.Product;
import com.example.ecommerce.product.ProductDto;
import com.example.ecommerce.cart.CartDto;
import com.example.ecommerce.size.Size;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDto {
    private Long cartItemId;
    @NotNull
    private Long productId;
    private Size size;
}
