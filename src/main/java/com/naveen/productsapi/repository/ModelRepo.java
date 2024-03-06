package com.naveen.productsapi.repository;

import com.naveen.productsapi.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModelRepo extends JpaRepository<Model, Long> {

    List<Model> findByGender(String gender);
}
