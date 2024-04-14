package com.example.ecommerce.cartItem;

import com.example.ecommerce.product.Product;
import com.example.ecommerce.cart.Cart;
import com.example.ecommerce.size.Size;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "cartItem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;
    @Embedded
    private Size size;

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + cartItemId +
                ", cart=" + cart.getCartId() +
                ", product=" + product.getName() +
                ", size =" + size.getName() +
                ", quantity=" + size.getQuantity() +
                '}';
    }
}
