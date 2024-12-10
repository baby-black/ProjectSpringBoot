package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.Cart;
import com.example.registrationlogindemo.entity.Product;
import com.example.registrationlogindemo.service.CartService;
import com.example.registrationlogindemo.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    // Thêm sản phẩm vào giỏ hàng
    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        Product product = productServiceImpl.getProductById(id); // Lấy sản phẩm theo ID
        if (product != null) {
            cartService.addProductToCart(product, session); // Thêm sản phẩm vào giỏ hàng
            redirectAttributes.addFlashAttribute("message", "Product added to cart successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Product not found!");
        }
        return "redirect:/cart"; // Chuyển hướng về trang giỏ hàng
    }

    // Hiển thị giỏ hàng
    @GetMapping()
    public String viewCart() {

        return "cart"; // Trả về trang giỏ hàng
    }

    // Xóa sản phẩm khỏi giỏ hàng
    @DeleteMapping("/remove/{id}")
    public String removeItem(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        cartService.remove(id, session); // Xóa sản phẩm khỏi giỏ hàng
        redirectAttributes.addFlashAttribute("message", "Product removed from cart successfully!");
        return "redirect:/cart"; // Quay lại trang giỏ hàng
    }

    @GetMapping("/carts/checkout")
    public String redirectToCheckout() {
        return "redirect:/checkout";
    }

}