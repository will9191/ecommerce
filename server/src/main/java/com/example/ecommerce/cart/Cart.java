package com.example.ecommerce.cart;

import com.example.ecommerce.cartItem.CartItem;
import com.example.ecommerce.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue
    private Long cartId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"cart", "tokens", "password", "authorities"})
    private User user;


    @OneToMany(mappedBy = "cartItemId", cascade = CascadeType.ALL)
    private Set<CartItem> cartItems;

    private double totalPrice = 0.0;

    public Cart() {
        this.cartItems = new HashSet<>();
        this.totalPrice = 0.0;
    }

}
