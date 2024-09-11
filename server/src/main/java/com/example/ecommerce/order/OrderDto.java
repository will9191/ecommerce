package com.example.ecommerce.order;

import com.example.ecommerce.cartItem.CartItem;
import com.example.ecommerce.product.Product;
import com.example.ecommerce.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class OrderDto {
    @Size(min = 1, message = "list cannot be empty")
    private List<Long> cartItemsId;
    private String orderStatus;
    private Double orderPrice;
}
