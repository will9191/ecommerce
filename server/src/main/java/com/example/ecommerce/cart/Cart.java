package com.example.ecommerce.cart;

import com.example.ecommerce.cartItem.CartItem;
import com.example.ecommerce.product.Product;
import com.example.ecommerce.size.Size;
import com.example.ecommerce.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"tokens", "password", "authorities"})
    private User user;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderColumn
//    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<CartItem> cartItems;


    private Double totalPrice = 0.0;



}
