package com.naveen.productsapi.service;

import com.naveen.productsapi.model.Model;
import com.naveen.productsapi.repository.ModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public ResponseEntity<?> getAllModels() {
        return new ResponseEntity<>(modelRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> getEachModel(Long mid) {
        Optional<Model> model = modelRepo.findById(mid);
        if(model.isPresent()){
            return new ResponseEntity<>(model.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Invalid ModelId or model not found", HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<?> deleteModel(Long mid) {
        Optional<Model> model = modelRepo.findById(mid);
        if(model.isPresent()){
            modelRepo.deleteById(mid);
            return new ResponseEntity<>("Model deleted", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Invalid ModelId or model not found", HttpStatus.CONFLICT);
        }

    }

    public ResponseEntity<?> updateModel(Long mid, Model model) {
        Optional<Model> modelSearched = modelRepo.findById(mid);
        if(modelSearched.isPresent()){
            model.setModelId(modelSearched.get().getModelId());
            return new ResponseEntity<>(modelRepo.save(model), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Invalid ModelId or model not found", HttpStatus.CONFLICT);
        }
    }
}
