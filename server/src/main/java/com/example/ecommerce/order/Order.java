package com.example.ecommerce.order;

import com.example.ecommerce.product.Product;
import com.example.ecommerce.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    @OneToMany
    private Set<Product> products;
    @OneToOne
    private User user;
    private String orderStatus;
    private Double orderPrice;
}
