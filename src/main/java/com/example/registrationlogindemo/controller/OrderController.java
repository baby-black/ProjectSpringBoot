package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.Orders;
import com.example.registrationlogindemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public Orders createOrder(@RequestParam Long userId) {
        return orderService.createOrder(userId);
    }
}
