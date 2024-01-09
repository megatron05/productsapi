package com.naveen.productsapi.controller;

import com.naveen.productsapi.DTO.InventoryRequest;
import com.naveen.productsapi.DTO.ProductRequest;
import com.naveen.productsapi.model.Product;
import com.naveen.productsapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{pid}")
    public ResponseEntity<?> getProduct(@PathVariable Long pid){
        return productService.getProduct(pid);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest productRequest){
        return productService.addProduct(productRequest);
    }


    @PostMapping("/toInventory")
    public ResponseEntity<?> addProductToInventory(@RequestBody InventoryRequest inventoryRequest){
        return productService.addProductToInventory(inventoryRequest);

    }

    @DeleteMapping("/{pid}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long pid){
        return productService.deleteProduct(pid);
    }

    @PutMapping("/{pid}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable Long pid){
        return productService.updateProduct(productRequest, pid);
    }
}
