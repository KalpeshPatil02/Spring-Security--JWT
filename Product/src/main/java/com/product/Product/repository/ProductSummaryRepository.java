package com.product.Product.repository;

import com.product.Product.entity.ProductSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSummaryRepository extends JpaRepository<ProductSummary, Long> {
}
