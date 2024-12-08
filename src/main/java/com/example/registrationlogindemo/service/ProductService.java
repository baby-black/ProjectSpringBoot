package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.entity.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    Product updateProduct(Long productId, Product product);
    void deleteProduct(Long productId);
    Product getProductById(Long productId);
    List<Product> getAllProducts();
    List<Product> searchProducts(String keyword); // Tìm kiếm sản phẩm theo tên

    Product findById(Long id);
}
