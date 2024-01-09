package com.naveen.productsapi.repository;

import com.naveen.productsapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
