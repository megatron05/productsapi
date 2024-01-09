package com.naveen.productsapi.controller;

import com.naveen.productsapi.model.Model;
import com.naveen.productsapi.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/model")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;

    @GetMapping
    public ResponseEntity<?> getAllModels(){ return modelService.getAllModels(); }

    @GetMapping("/{mid}")
    public ResponseEntity<?> getEachModel(@PathVariable Long mid){ return modelService.getEachModel(mid); }
    @PostMapping
    public ResponseEntity<?> addModel(@RequestBody Model model){
        return modelService.addModel(model);
    }
    @DeleteMapping("/{mid}")
    public ResponseEntity<?> deleteModel(@PathVariable Long mid){ return modelService.deleteModel(mid); }

    @PutMapping("/{mid}")
    public ResponseEntity<?> updateModel(@PathVariable Long mid, @RequestBody Model model){ return modelService.updateModel(mid, model); }


}
