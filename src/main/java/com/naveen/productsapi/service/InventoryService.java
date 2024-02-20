package com.naveen.productsapi.service;


import com.naveen.productsapi.dto.InventoryRequest;

import com.naveen.productsapi.dto.InventoryResponse;
import com.naveen.productsapi.model.Inventory;
import com.naveen.productsapi.model.Product;
import com.naveen.productsapi.repository.InventoryRepo;
import com.naveen.productsapi.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepo inventoryRepo;
    private final ProductRepo productRepo;
    public List<Inventory> getAllProductsFromInventory() {
        return inventoryRepo.findAll();
    }


    public ResponseEntity<?> addProductToInventory(InventoryRequest inventoryRequest) {
        Optional<Product> product = productRepo.findById(inventoryRequest.getProductId());
        if(!product.isPresent()){
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

    public ResponseEntity<String> deleteProductFromInventory(Long pid) {
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

    public ResponseEntity<?> checkForProduct(List<InventoryRequest> inventoryRequests) {
        List<InventoryResponse> inventoryResponses = inventoryRequests.stream()
                .map(inventoryRequest -> InventoryResponse.builder()
                        .productId(inventoryRequest.getProductId())
                        .requestedQuantity(inventoryRequest.getQuantity())
                        .existingQuantity((inventoryRepo.findByProduct(productRepo.findById(inventoryRequest.getProductId()).get()).get()).getQuantity())
                        .isInStock((inventoryRequest.getQuantity()) < ((inventoryRepo.findByProduct(productRepo.findById(inventoryRequest.getProductId()).get()).get()).getQuantity()))
                        .build())
                .toList();
        return new ResponseEntity<>(inventoryResponses, HttpStatus.OK);
    }
}
