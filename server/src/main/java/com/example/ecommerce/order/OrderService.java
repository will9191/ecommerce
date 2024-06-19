package com.example.ecommerce.order;

import com.example.ecommerce.cart.Cart;
import com.example.ecommerce.cart.CartRepository;
import com.example.ecommerce.cartItem.CartItem;
import com.example.ecommerce.cartItem.CartItemRepository;
import com.example.ecommerce.orderItem.OrderItem;
import com.example.ecommerce.orderItem.OrderItemRepository;
import com.example.ecommerce.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final CartItemRepository cartItemRepository;
    private final OrderItemRepository orderItemRepository;
private final CartRepository cartRepository;
    public void saveOrder(OrderDto orderDto, User user) {
        Order order = new Order();
        order.setOrderItems(new ArrayList<>());
        Cart cart = cartRepository.findByUser(user);

        List<OrderItem> orderItemList = order.getOrderItems();
        for (Long id : orderDto.getCartItemsId()) {
            var cartItem = cartItemRepository.findById(id).orElse(null);

            assert cartItem != null;
//            OrderItem orderItem =new OrderItem();
//            orderItem.setProduct(cartItem.getProduct());
//            orderItem.setPrice(cartItem.getPrice());
//            orderItem.setOrder(order);
//            orderItem.setSize(cartItem.getSize());


         OrderItem orderItem =  OrderItem.builder().product(cartItem.getProduct()).price(cartItem.getPrice()).order(order).size(cartItem.getSize()).build();

            orderItemRepository.save(orderItem);
            orderItemList.add(orderItem);
            cart.getCartItems().remove(cartItem);
        }

        cartRepository.save(cart);

        order.setUser(user);
        order.setOrderItems(orderItemList);
        order.setOrderStatus("Recebido");
        order.setOrderPrice(getTotalPrice(orderItemList));
        repository.save(order);
    }

    public List<Order> getOrdersByUser(User user){
       return repository.findByUser(user);
    }

    public Double getTotalPrice(List<OrderItem> orderItems) {
        var totalPrice = 0.0;
        for (OrderItem orderItem : orderItems) {
            var quantity = orderItem.getSize().getQuantity();

            totalPrice += orderItem.getPrice() * quantity;
        }
        return totalPrice;
    }
}
