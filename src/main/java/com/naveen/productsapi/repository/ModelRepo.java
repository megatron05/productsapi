package com.naveen.productsapi.repository;

import com.naveen.productsapi.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepo extends JpaRepository<Model, Long> {
}
