package com.example.ecommerce.payment;

import com.example.ecommerce.order.Order;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToOne
    private Order order;
    private double amount;
    private PaymentStatus paymentStatus;
}
