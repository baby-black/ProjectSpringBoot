package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.Product;
import com.example.registrationlogindemo.service.impl.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    private final ProductServiceImpl productServiceImpl;

    public AdminController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @GetMapping("/admin")
    public String admin(@RequestParam(value = "query", required = false) String query, Model model) {
        List<Product> products;
            products = productServiceImpl.getAllProducts();
        model.addAttribute("products", products);
        return "admin";
    }
}
