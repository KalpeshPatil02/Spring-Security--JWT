package com.product.Product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ProductSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;


    @NonNull
    private String shortDescription;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "product_id")
    @NonNull
    private Product product;
}

