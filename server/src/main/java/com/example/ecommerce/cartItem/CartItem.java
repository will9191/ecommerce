package com.example.ecommerce.cartItem;

import com.example.ecommerce.cart.Cart;
import com.example.ecommerce.product.Product;
import com.example.ecommerce.size.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cartItems")
@Transactional
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Cart cart;
    @Embedded
    private Size size;
}
