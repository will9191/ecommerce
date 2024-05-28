package com.example.ecommerce.order;

import com.example.ecommerce.product.Product;
import com.example.ecommerce.user.User;
import lombok.Data;

import java.util.Set;

@Data
public class OrderDto {
    private Set<Product> products;
    private String orderStatus;
    private Double orderPrice;
}
