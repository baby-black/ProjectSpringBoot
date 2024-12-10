package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.entity.Cart;
import com.example.registrationlogindemo.entity.CartItem;
import com.example.registrationlogindemo.entity.Product;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public abstract class CartService {

    // Lấy giỏ hàng từ session của người dùng
    public List<CartItem> getCartItemsForUser(HttpSession session) {
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
            session.setAttribute("cartItems", cartItems);
        }
        return cartItems;
    }

    // Thêm sản phẩm vào giỏ hàng
    public void addProductToCart(Product product, HttpSession session) {
        List<CartItem> cartItems = getCartItemsForUser(session);
        for (CartItem cart : cartItems) {
            if (cart.getProduct().getId().equals(product.getId())) {
                cart.setQuantity(cart.getQuantity() + 1);
                return;
            }
        }
        cartItems.add(new CartItem(product, 1));
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public void remove(Long productId, HttpSession session) {
        List<CartItem> cartItems = getCartItemsForUser(session);
        cartItems.removeIf(cart -> cart.getProduct().getId().equals(productId));
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    public void updateQuantity(Long productId, String action, HttpSession session) {
        List<CartItem> cartItems = getCartItemsForUser(session);
        for (CartItem cart : cartItems) {
            if (cart.getProduct().getId().equals(productId)) {
                if ("increase".equals(action)) {
                    cart.setQuantity(cart.getQuantity() + 1);
                } else if ("decrease".equals(action)) {
                    if (cart.getQuantity() > 1) {
                        cart.setQuantity(cart.getQuantity() - 1);
                    }
                }
                break;
            }
        }
    }



    public abstract void addProductToCart(Product product);

    public abstract List<CartItem> getCartItemsForUser();

    public abstract void addProductToCart(Product product, Long userId);

    public abstract List<Cart> getCartItemsForUser(Long userId);

    public abstract void remove(Long id);

    public abstract void updateQuantity(Long id, String action);

    public abstract void updateQuantity(Long productId, String action, Long userId);
}

