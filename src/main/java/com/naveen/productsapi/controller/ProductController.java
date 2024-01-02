package com.naveen.productsapi.controller;

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
    public ResponseEntity<List<Product>>getAllProducts(){
        return new ResponseEntity<List<Product>>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return new ResponseEntity<Product>(productService.addProduct(product), HttpStatus.CREATED);
    }

    @PostMapping("/toInventory/{pid}/{qty}")
    public ResponseEntity<?> addProductToInventory(@PathVariable Long pid, @PathVariable Long qty){
        return productService.addProductToInventory(pid,qty);
    }

    @DeleteMapping("/{pid}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long pid){
        return productService.deleteProduct(pid);
    }

}
