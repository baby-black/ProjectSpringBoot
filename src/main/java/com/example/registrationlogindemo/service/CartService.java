package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.entity.Cart;
import com.example.registrationlogindemo.entity.Product;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public abstract class CartService {

    // Lấy giỏ hàng từ session của người dùng
    public List<Cart> getCartItemsForUser(HttpSession session) {
        // Kiểm tra nếu giỏ hàng đã tồn tại trong session
        List<Cart> cartItems = (List<Cart>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
            session.setAttribute("cartItems", cartItems); // Tạo mới giỏ hàng nếu chưa có
        }
        return cartItems;
    }

    // Thêm sản phẩm vào giỏ hàng
    public void addProductToCart(Product product, HttpSession session) {
        List<Cart> cartItems = getCartItemsForUser(session); // Lấy giỏ hàng từ session
        // Kiểm tra nếu sản phẩm đã tồn tại trong giỏ hàng
        for (Cart cart : cartItems) {
            if (cart.getProduct().getId().equals(product.getId())) {
                // Nếu sản phẩm đã có trong giỏ hàng, chỉ cần tăng số lượng
                cart.setQuantity(cart.getQuantity() + 1);
                return; // Không thêm mới sản phẩm, chỉ tăng số lượng
            }
        }
        // Nếu sản phẩm chưa có trong giỏ, tạo mới giỏ hàng cho sản phẩm đó
        cartItems.add(new Cart(product, 1)); // Thêm sản phẩm với số lượng 1 vào giỏ
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public void remove(Long productId, HttpSession session) {
        List<Cart> cartItems = getCartItemsForUser(session); // Lấy giỏ hàng từ session
        cartItems.removeIf(cart -> cart.getProduct().getId().equals(productId)); // Xóa sản phẩm theo ID
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    public void updateQuantity(Long productId, String action, HttpSession session) {
        List<Cart> cartItems = getCartItemsForUser(session); // Lấy giỏ hàng từ session
        for (Cart cart : cartItems) {
            if (cart.getProduct().getId().equals(productId)) {
                if ("increase".equals(action)) {
                    cart.setQuantity(cart.getQuantity() + 1); // Tăng số lượng
                } else if ("decrease".equals(action)) {
                    if (cart.getQuantity() > 1) {
                        cart.setQuantity(cart.getQuantity() - 1); // Giảm số lượng nếu số lượng lớn hơn 1
                    }
                }
                break;
            }
        }
    }

    public abstract void addProductToCart(Product product);

    public abstract List<Cart> getCartItemsForUser();

    public abstract void remove(Long id);

    public abstract void updateQuantity(Long id, String action);
}
