package com.naveen.productsapi.repository;

import com.naveen.productsapi.model.Inventory;
import com.naveen.productsapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepo extends JpaRepository<Inventory, Integer> {

    Optional<Inventory> findByProduct(Product product);
}
