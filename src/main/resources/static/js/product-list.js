// Tìm kiếm sản phẩm
document.getElementById("searchBar").addEventListener("input", function() {
    let searchTerm = this.value.toLowerCase(); // Lấy từ khóa tìm kiếm
    let products = document.querySelectorAll(".product-item"); // Tìm tất cả sản phẩm

    products.forEach(function(product) {
        let productName = product.querySelector(".card-title").textContent.toLowerCase(); // Lấy tên sản phẩm
        if (productName.includes(searchTerm)) {
            product.style.display = ""; // Hiển thị sản phẩm nếu khớp với từ khóa
        } else {
            product.style.display = "none"; // Ẩn sản phẩm nếu không khớp
        }
    });
});