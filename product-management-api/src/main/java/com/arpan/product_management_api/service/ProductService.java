package com.arpan.product_management_api.service;

import com.arpan.product_management_api.exception.ProductNotFoundException;
import com.arpan.product_management_api.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();

    public ProductService() {
        products.add(new Product(1, "Laptop", "ELECTRONICS", 55000.0, 10));
        products.add(new Product(2, "Phone", "ELECTRONICS", 20000.0, 25));
        products.add(new Product(3, "T-Shirt", "CLOTHING", 499.0, 100));
        products.add(new Product(4, "Jeans", "CLOTHING", 1299.0, 50));
        products.add(new Product(5, "Rice 5kg", "FOOD", 350.0, 200));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
    public List<Product> getByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Product> getByMaxPrice(double maxPrice) {
        return products.stream()
                .filter(p -> p.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    public Product updateProduct(int id, Product updated) {
        Product existing = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(id));

        products.remove(existing);
        products.add(new Product(id, updated.getName(), updated.getCategory(),
                updated.getPrice(), updated.getStock()));
        return updated;
    }

    public void deleteProduct(int id) {
        Product existing = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(id));

        products.remove(existing);
    }

    public Product updateStock(int id, int quantity) {
        Product existing = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(id));

        products.remove(existing);
        Product updated = new Product(id, existing.getName(), existing.getCategory(),
                existing.getPrice(), quantity);
        products.add(updated);
        return updated;
    }
}