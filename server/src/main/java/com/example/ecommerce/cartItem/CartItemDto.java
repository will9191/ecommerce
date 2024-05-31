package com.example.ecommerce.cartItem;

import com.example.ecommerce.size.Size;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    private Long productId;
    @Embedded
    private Size size;
}
