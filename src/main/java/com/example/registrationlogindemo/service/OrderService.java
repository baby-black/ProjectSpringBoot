package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.entity.Orders;

public interface OrderService {
    Orders createOrder(Long userId);
}
