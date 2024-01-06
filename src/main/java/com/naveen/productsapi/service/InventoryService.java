package com.naveen.productsapi.service;

import com.naveen.productsapi.Builder.ProductQuantityBuilder;
import com.naveen.productsapi.DTO.ProductQuantity;
import com.naveen.productsapi.model.Inventory;
import com.naveen.productsapi.model.Product;
import com.naveen.productsapi.repository.InventoryRepo;
import com.naveen.productsapi.repository.ProductRepo;
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

    public Inventory addProductToInventory(Inventory productStats) {

        return inventoryRepo.save(productStats);
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

    public ResponseEntity<?> updateProductInInventory(Integer pid, Inventory inventory) {
        Optional<Product> product = productRepo.findById(pid);
        if(product.isPresent()){
            Optional<Inventory> existingInventory = inventoryRepo.findByProduct(product.get());
            if(existingInventory.isPresent()) {
                inventory.setId(existingInventory.get().getId());
                inventoryRepo.save(inventory);
                return new ResponseEntity<>(inventoryRepo.save(inventory), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Product  not in Inventory", HttpStatus.CONFLICT);
            }
        }
        else{
            return new ResponseEntity<>("Invalid Product ID", HttpStatus.CONFLICT);
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
