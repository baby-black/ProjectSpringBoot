package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long userId); // Tìm giỏ hàng theo userId
    Cart findByUserIdAndProductId(Long userId, Long productId); // Tìm sản phẩm trong giỏ hàng của người dùng
}

