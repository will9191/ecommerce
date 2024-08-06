package com.example.ecommerce.order;

import com.example.ecommerce.orderItem.OrderItem;
import com.example.ecommerce.payment.Payment;
import com.example.ecommerce.product.Product;
import com.example.ecommerce.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_order")
@Transactional
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"tokens", "password", "authorities"})
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JsonIgnoreProperties({"order"})
    private Payment payment;
    private OrderStatus orderStatus;
    private Double orderPrice;
}
