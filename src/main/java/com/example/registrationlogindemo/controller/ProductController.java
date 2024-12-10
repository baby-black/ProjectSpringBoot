package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.Product;
import com.example.registrationlogindemo.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Hiển thị danh sách sản phẩm với tính năng tìm kiếm
    @GetMapping
    public String listProducts(@RequestParam(value = "query", required = false) String query, Model model) {
        List<Product> products = (query != null && !query.isEmpty())
                ? productService.searchProducts(query) // Tìm kiếm theo từ khóa
                : productService.getAllProducts(); // Lấy tất cả sản phẩm

        model.addAttribute("products", products);
        model.addAttribute("query", query); // Truyền từ khóa để hiển thị lại trên giao diện
        return "product-list"; // Trang hiển thị danh sách sản phẩm
    }

    // Hiển thị form thêm sản phẩm
    @GetMapping("/new")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form"; // Trang form sản phẩm
    }

    // Xử lý thêm sản phẩm mới
    @PostMapping
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/products"; // Quay lại danh sách sản phẩm
    }

    // Hiển thị form sửa sản phẩm
    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/products"; // Nếu không tìm thấy sản phẩm, quay lại danh sách
        }
        model.addAttribute("product", product);
        return "product-form"; // Dùng lại trang form
    }

    // Xử lý cập nhật sản phẩm
    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute("product") Product product) {
        productService.updateProduct(id, product);
        return "redirect:/products";
    }

    // Xóa sản phẩm
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    // Hiển thị chi tiết sản phẩm
    @GetMapping("/product-detail/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/products"; // Nếu không tìm thấy sản phẩm, quay lại danh sách
        }
        model.addAttribute("product", product);
        return "product-detail"; // Trang chi tiết sản phẩm
    }

    // ** API Endpoint **

    // Lấy danh sách sản phẩm (API)
    @GetMapping("/api")
    @ResponseBody
    public List<Product> getAllProductsApi() {
        return productService.getAllProducts();
    }

    // Lấy thông tin chi tiết sản phẩm (API)
    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Product> getProductByIdApi(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // Thêm sản phẩm mới qua API
    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Product> addProductApi(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    // Xóa sản phẩm qua API
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteProductApi(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Sản phẩm đã được xóa!");
    }

    // Cập nhật sản phẩm qua API
    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Product> updateProductApi(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }
    @GetMapping("/products")
    public String showProducts(Model model, @RequestParam(value = "search", required = false) String search) {
        List<Product> products = productService.findProductsByName(search); // Giả sử bạn có một phương thức tìm kiếm
        model.addAttribute("products", products);
        return "product-list";
    }
}
