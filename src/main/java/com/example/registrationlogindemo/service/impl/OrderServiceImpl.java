package com.example.registrationlogindemo.service.impl;

import com.example.registrationlogindemo.entity.Cart;
import com.example.registrationlogindemo.entity.Orders;
import com.example.registrationlogindemo.entity.OrderItem;
import com.example.registrationlogindemo.repository.CartRepository;
import com.example.registrationlogindemo.repository.OrderItemRepository;
import com.example.registrationlogindemo.repository.OrderRepository;
import com.example.registrationlogindemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public Orders createOrder(Long userId) {
        List<Cart> cartItems = cartRepository.findAll()
                .stream()
                .filter(cart -> cart.getUserId().equals(userId))
                .toList();

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Giỏ hàng trống!");
        }

        Orders order = new Orders();
        order.setUserId(userId);
        order.setStatus("Pending");

        double totalPrice = 0;
        for (Cart cart : cartItems) {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(cart.getProduct());
            item.setQuantity(cart.getQuantity());
            item.setPrice(cart.getTotalPrice());
            totalPrice += cart.getTotalPrice();

            order.getOrderItems().add(item);
        }

        order.setTotalPrice(totalPrice);
        Orders savedOrder = orderRepository.save(order);

        // Xóa giỏ hàng sau khi tạo đơn hàng
        cartRepository.deleteAll(cartItems);

        return savedOrder;
    }
}
