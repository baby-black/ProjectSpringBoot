package com.example.registrationlogindemo.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId; // Mỗi giỏ hàng liên kết với một người dùng

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product; // Sản phẩm liên kết với giỏ hàng

    @Column(name = "product_id", nullable = false)
    private Long productId; // ID sản phẩm được thêm vào giỏ hàng

    @Column(name = "quantity", nullable = false)
    private int quantity; // Số lượng sản phẩm

    @Column(name = "price", nullable = false)
    private double price; // Giá sản phẩm (đơn lẻ)

    @Column(name = "image_url")
    private String imageUrl; // Đường dẫn hình ảnh sản phẩm

    @Transient
    private double totalPrice; // Giá tổng của sản phẩm trong giỏ (Không lưu trong DB)

    public Cart() {
    }

    // Constructor
    public Cart(Long userId, Long productId, int quantity, double price, String imageUrl) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = price * quantity;
        this.imageUrl = imageUrl; // Lưu đường dẫn hình ảnh
    }

        public Cart(Product product, int i) {
        productId = product.getId();
    }

    // Getters và Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product; // Trả về đối tượng Product
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = this.price * quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        this.totalPrice = this.price * this.quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return quantity == cart.quantity && Double.compare(cart.price, price) == 0 && Objects.equals(id, cart.id) && Objects.equals(userId, cart.userId) && Objects.equals(productId, cart.productId) && Objects.equals(imageUrl, cart.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, productId, quantity, price, imageUrl);
    }
}
