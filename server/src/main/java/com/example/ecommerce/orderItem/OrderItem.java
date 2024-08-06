package com.example.ecommerce.orderItem;

import com.example.ecommerce.order.Order;
import com.example.ecommerce.product.Product;
import com.example.ecommerce.size.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

@Getter
@Setter@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Transactional
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    @Embedded
    private Size size;
    private Double price;
}
