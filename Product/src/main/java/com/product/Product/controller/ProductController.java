package com.product.Product.controller;
import com.product.Product.entity.Product;
import com.product.Product.entity.Role;
import com.product.Product.entity.User;
import com.product.Product.sevice.ProductService;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Example: Only ADMIN can create products
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product created = productService.createProduct(product);
        return ResponseEntity.status(201).body(created);
    }

    // Example: Any authenticated user can view products
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // Example: Only ADMIN can delete products
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}