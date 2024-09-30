package com.example.ecommerce.cart;

import com.example.ecommerce.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartProductDTO {
    private Cart cart;
    private Product product;
}
