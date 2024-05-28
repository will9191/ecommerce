package com.example.ecommerce.cart;

import com.example.ecommerce.product.Product;
import com.example.ecommerce.size.Size;
import com.example.ecommerce.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue
    private Long cartId;

    @ManyToOne
    @JsonIgnoreProperties({"carts", "tokens", "password", "authorities"})
    private User user;

    @OneToOne
    @JsonIgnoreProperties
    private Product product;
    @Embedded
    private Size size;

    private Double price = 0.0;


}
