package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
