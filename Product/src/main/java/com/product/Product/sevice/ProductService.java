package com.product.Product.sevice;


import com.product.Product.entity.Product;
import com.product.Product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    // Constructor Injection (Recommended)
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Create
    @Transactional
    public Product createProduct(Product product) {

        if (product.getSummary() != null) {
            product.getSummary().setProduct(product); // 🔥 THIS IS IMPORTANT
        }

        return productRepository.save(product);
    }


    // Get All
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get By Id
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // Update
    @Transactional
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = getProductById(id);
        existing.setName(updatedProduct.getName());
        existing.setPrice(updatedProduct.getPrice());
        return productRepository.save(existing);
    }

    // Delete
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
