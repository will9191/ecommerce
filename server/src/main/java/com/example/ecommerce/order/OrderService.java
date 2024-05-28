package com.example.ecommerce.order;

import com.example.ecommerce.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;

    public void saveOrder(OrderDto orderDto, User user) {
        Order order = new Order();
        order.setUser(user);

    }
}
