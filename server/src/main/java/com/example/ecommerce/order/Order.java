package com.example.ecommerce.order;

import com.example.ecommerce.orderItem.OrderItem;
import com.example.ecommerce.product.Product;
import com.example.ecommerce.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"tokens", "password", "authorities"})
    private User user;
    private String orderStatus;
    private Double orderPrice;
}
