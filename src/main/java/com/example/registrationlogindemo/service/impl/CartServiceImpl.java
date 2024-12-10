package com.example.registrationlogindemo.service.impl;

import com.example.registrationlogindemo.entity.Cart;
import com.example.registrationlogindemo.entity.CartItem;
import com.example.registrationlogindemo.entity.Product;
import com.example.registrationlogindemo.repository.CartRepository;
import com.example.registrationlogindemo.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl extends CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void addProductToCart(Product product) {

    }

    @Override
    public List<CartItem> getCartItemsForUser() {
        return List.of();
    }

    @Override
    public void addProductToCart(Product product, Long userId) {
        Optional<Cart> existingCartItem = Optional.ofNullable(cartRepository.findByUserIdAndProductId(userId, product.getId()));
        if (existingCartItem.isPresent()) {
            Cart cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItem.setTotalPrice(cartItem.getQuantity() * product.getPrice());
            cartRepository.save(cartItem);
        } else {
            Cart cartItem = new Cart();
            cartItem.setUserId(userId);
            cartItem.setProductId(product.getId());
            cartItem.setQuantity(1);
            cartItem.setTotalPrice(product.getPrice());
            cartRepository.save(cartItem);
        }
    }

    @Override
    public List<Cart> getCartItemsForUser(Long userId) {
        return cartRepository.findAll()
                .stream()
                .filter(cart -> cart.getUserId().equals(userId))
                .toList();
    }

    @Override
    public void remove(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void updateQuantity(Long id, String action) {

    }

    @Override
    public void updateQuantity(Long productId, String action, Long userId) {
        Optional<Cart> existingCartItem = Optional.ofNullable(cartRepository.findByUserIdAndProductId(userId, productId));
        if (existingCartItem.isPresent()) {
            Cart cartItem = existingCartItem.get();
            if ("increase".equals(action)) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
            } else if ("decrease".equals(action) && cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
            } else {
                cartRepository.delete(cartItem);
                return;
            }
            cartItem.setTotalPrice(cartItem.getQuantity() * cartItem.getProduct().getPrice());
            cartRepository.save(cartItem);
        }
    }
}
