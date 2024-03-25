package com.naveen.productsapi.controller;

import com.naveen.productsapi.ProductsApiApplication;
import com.naveen.productsapi.dto.InventoryRequest;
import com.naveen.productsapi.dto.ProductRequest;
import com.naveen.productsapi.model.Product;
import com.naveen.productsapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductsApiApplication.class);
    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        logger.info("entered get of inventory list");
        return productService.getAllProducts();
    }

    @CrossOrigin
    @GetMapping("/product/{pid}")
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

    @DeleteMapping("/product/{pid}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long pid){
        return productService.deleteProduct(pid);
    }

    @PutMapping("/product/{pid}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable Long pid){
        return productService.updateProduct(productRequest, pid);
    }

    @CrossOrigin
    @GetMapping("/{pathname}")
    public ResponseEntity<?> getProductsWithGender(@PathVariable String pathname){
        return productService.getProductsWithFilter(pathname);
    }
}
