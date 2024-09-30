package com.example.ecommerce.order;

import com.example.ecommerce.cart.Cart;
import com.example.ecommerce.cart.CartRepository;
import com.example.ecommerce.cartItem.CartItem;
import com.example.ecommerce.cartItem.CartItemRepository;
import com.example.ecommerce.orderItem.OrderItem;
import com.example.ecommerce.orderItem.OrderItemRepository;
import com.example.ecommerce.payment.Payment;
import com.example.ecommerce.payment.PaymentDto;
import com.example.ecommerce.payment.PaymentRepository;
import com.example.ecommerce.payment.PaymentStatus;
import com.example.ecommerce.product.Product;
import com.example.ecommerce.size.Size;
import com.example.ecommerce.user.User;
import com.example.ecommerce.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final CartItemRepository cartItemRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    public Order saveOrder(OrderDto orderDto, User user) {
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


            OrderItem orderItem = OrderItem.builder().product(cartItem.getProduct()).price(cartItem.getPrice()).order(order).size(cartItem.getSize()).build();

            orderItemRepository.save(orderItem);
            orderItemList.add(orderItem);
            cart.getCartItems().remove(cartItem);
        }
        Payment payment = new Payment();
        payment.setAmount(getTotalPrice(orderItemList));
        payment.setPaymentStatus(PaymentStatus.NOTPAID);
        payment.setOrder(order);
        paymentRepository.save(payment);

        cartRepository.save(cart);

        order.setUser(user);
        order.setOrderItems(orderItemList);
        order.setOrderStatus(OrderStatus.CREATED);
        order.setOrderPrice(getTotalPrice(orderItemList));
        order.setPayment(payment);
      return  repository.save(order);

    }



    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }

    public void payOrder(PaymentDto paymentDto, User user) throws Exception {
        Optional<Payment> payment = paymentRepository.findById(paymentDto.getPaymentId());
        if (payment.isEmpty()) {
            throw new Exception();
        }
        if (user.getCoins() > payment.get().getAmount()) {
            payment.get().setPaymentStatus(PaymentStatus.PAID);
            payment.get().getOrder().setOrderStatus(OrderStatus.PAID);
            paymentRepository.save(payment.get());

            user.setCoins(user.getCoins() - payment.get().getAmount());
            userRepository.save(user);
        } else {
            throw new Exception("Not enough coins!");
        }
    }

    public List<Order> getOrdersByUser(User user) {
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

    public List<Order> findAll(){
        return repository.findAll();
    }
}
