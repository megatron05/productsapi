package com.naveen.productsapi.repository;

import com.naveen.productsapi.model.Model;
import com.naveen.productsapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByModelIn(List<Model> models);

}
