package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String name); // Tìm kiếm không phân biệt chữ hoa/thường
}
