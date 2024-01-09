package com.naveen.productsapi.service;


import com.naveen.productsapi.dto.InventoryRequest;

import com.naveen.productsapi.Builder.ProductQuantityBuilder;
import com.naveen.productsapi.DTO.ProductQuantity;

import com.naveen.productsapi.model.Inventory;
import com.naveen.productsapi.model.Product;
import com.naveen.productsapi.repository.InventoryRepo;
import com.naveen.productsapi.repository.ProductRepo;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    @Autowired
    ProductQuantity productQuantity;
    @Autowired
    ProductQuantityBuilder productQuantityBuilder;

    private final InventoryRepo inventoryRepo;
    private final ProductRepo productRepo;
    public List<Inventory> getAllProductsFromInventory() {
        return inventoryRepo.findAll();
    }


    public ResponseEntity<?> addProductToInventory(InventoryRequest inventoryRequest) {
        Optional<Product> product = productRepo.findById(inventoryRequest.getProductId());
        if(product.isPresent()){
            return new ResponseEntity<>("Invalid Product id", HttpStatus.CONFLICT);
        }
        if(inventoryRepo.findByProduct(product.get()).isPresent()){
            return new ResponseEntity<>("Product already exists in inventory", HttpStatus.CONFLICT);
        }
        else{
            Inventory inventory = Inventory.builder()
                    .product(product.get())
                    .quantity(inventoryRequest.getQuantity())
                    .build();
            return new ResponseEntity<>(inventoryRepo.save(inventory), HttpStatus.CONFLICT);
        }


    }

    public ResponseEntity<String> deleteProductFromInventory(Integer pid) {
        Optional<Product> product = productRepo.findById(pid);
        if(product.isPresent()){
            Optional<Inventory> productToBeDeleted = inventoryRepo.findByProduct(product.get());
            if(productToBeDeleted.isPresent()) {
                inventoryRepo.delete(productToBeDeleted.get());
                return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
            }
        }
        else{
            return new ResponseEntity<>("Product id not found or Invalid Product ID", HttpStatus.CONFLICT);
        }

        return null;
    }


    public ResponseEntity<?> updateProductInInventory(InventoryRequest inventoryRequest, Long inventoryId) {
        Optional<Inventory> inventory = inventoryRepo.findById(inventoryId);
        if (inventory.isPresent()) {
            Optional<Product> product = productRepo.findById(inventoryRequest.getProductId());
            if (product.isPresent()) {
                inventory.get().setProduct(product.get());
                inventory.get().setQuantity(inventoryRequest.getQuantity());
                return new ResponseEntity<>(inventoryRepo.save(inventory.get()), HttpStatus.CREATED);

            } else {
                return new ResponseEntity<>("Invalid Product id or Product not found", HttpStatus.CONFLICT);

            }

        } else {
            return new ResponseEntity<>("Invalid Inventory id ", HttpStatus.CONFLICT);
        }

    }


    public ResponseEntity<?> checkForProduct(String productId, Integer quantity) {
        Integer product = Integer.parseInt(productId);
        Optional <Inventory> inventory = inventoryRepo.findByProduct(productRepo.findById(product).get());
        if (inventory.isPresent()) {
            Inventory i = inventory.get();
            productQuantity = productQuantityBuilder.buildProductQuantity(i);
            if (i.getQuantity() < quantity) {
                productQuantity.setIsPresentInInventory(Boolean.FALSE);
                return new ResponseEntity<>(productQuantity, HttpStatus.OK);
            } else {
                productQuantity.setIsPresentInInventory(Boolean.TRUE);
                return new ResponseEntity<>(productQuantity, HttpStatus.OK);
            }
        }
        else{
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }
    }


}
