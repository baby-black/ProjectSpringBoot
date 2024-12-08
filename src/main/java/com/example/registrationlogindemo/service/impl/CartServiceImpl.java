package com.example.registrationlogindemo.service.impl;

import com.example.registrationlogindemo.entity.Cart;
import com.example.registrationlogindemo.entity.Product;
import com.example.registrationlogindemo.repository.CartRepository;
import com.example.registrationlogindemo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl extends CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void addProductToCart(Product product) {
        Cart cartItem = new Cart(product, 1);
        cartItem.setProductId(product.getId());
        cartItem.setQuantity(1); // Mặc định là thêm 1 sản phẩm vào giỏ
        cartRepository.save(cartItem); // Lưu sản phẩm vào giỏ hàng
    }

    @Override
    public List<Cart> getCartItemsForUser() {
        return cartRepository.findAll(); // Trả về tất cả sản phẩm trong giỏ hàng
    }

    @Override
    public void remove(Long id) {
        cartRepository.deleteById(id); // Xóa sản phẩm khỏi giỏ hàng
    }

    @Override
    public void updateQuantity(Long id, String action) {
        Cart cartItem = cartRepository.findById(id).orElse(null);
        if (cartItem != null) {
            if ("increase".equals(action)) {
                cartItem.setQuantity(cartItem.getQuantity() + 1); // Tăng số lượng
            } else if ("decrease".equals(action) && cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1); // Giảm số lượng
            }
            cartRepository.save(cartItem); // Lưu lại giỏ hàng sau khi thay đổi
        }
    }
}
