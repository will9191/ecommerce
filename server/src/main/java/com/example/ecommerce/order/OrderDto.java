package com.example.ecommerce.order;

import com.example.ecommerce.cartItem.CartItem;
import com.example.ecommerce.product.Product;
import com.example.ecommerce.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class OrderDto {
    private List<Long> cartItemsId;
    private String orderStatus;
    private Double orderPrice;
}
