package com.naveen.productsapi.service;

import com.naveen.productsapi.model.Model;
import com.naveen.productsapi.repository.ModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ModelService {
    @Autowired
    ModelRepo modelRepo;
    public ResponseEntity<?> addModel(Model model){
        try{
            return new ResponseEntity<>(modelRepo.save(model), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Model already exists", HttpStatus.CONFLICT);
        }

    }
}
