package com.example.registrationlogindemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class CheckoutController {

    @GetMapping("/checkout")
    public String checkoutPage() {
        return "checkout"; // Tên file HTML trong thư mục templates/checkout.html
    }

    @PostMapping("/checkout")
    public String processCheckout(@RequestParam Map<String, String> formData, Model model) {
        model.addAttribute("message", "Order placed successfully!");
        return "/success"; // Một trang thành công sau khi đặt hàng
    }

        @PostMapping("/payment-success")
        public String showSuccessPage() {
            return "/success"; // Tên file là success.html
        }
}
