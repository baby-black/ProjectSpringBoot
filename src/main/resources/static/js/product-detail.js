// product-detail.js

document.addEventListener('DOMContentLoaded', function() {
    const productId = document.querySelector('meta[name="product-id"]').getAttribute('content');
    const reviewsList = document.getElementById("reviews-list");

    // Fetch feedbacks from API
    fetch('/products/api/' + productId + '/reviews')
        .then(response => response.json())
        .then(data => {
            data.forEach(review => {
                const listItem = document.createElement("li");
                listItem.classList.add("list-group-item");
                listItem.textContent = review;
                reviewsList.appendChild(listItem);
            });
        })
        .catch(error => console.error("Error fetching reviews:", error));
});
