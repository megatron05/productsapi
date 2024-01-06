package com.naveen.productsapi.service;

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
public class ProductService {

    private final ProductRepo productRepo;
    private final InventoryRepo inventoryRepo;
    public List<Product> getAllProducts() {
        return  productRepo.findAll();
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public ResponseEntity<?> addProductToInventory(Integer pid, Integer qty) {
        Optional<Product> product = productRepo.findById(pid);
        if(product.isPresent()) {
            Optional<Inventory> inventory = inventoryRepo.findByProduct(product.get());
            if(inventory.isPresent()){
                return new ResponseEntity<>("Already exits in inventory", HttpStatus.CONFLICT);
            }
            else{
                Inventory inventory1 = new Inventory();
                inventory1.setProduct(product.get());
                inventory1.setQuantity(qty);
                return new ResponseEntity<>(inventoryRepo.save(inventory1),HttpStatus.CREATED);

            }

        }
        return new ResponseEntity<>("Invalid Product Id", HttpStatus.CONFLICT);
    }

    public ResponseEntity<?> deleteProduct(Integer pid) {
        Optional<Product> product = productRepo.findById(pid);
        if(product.isPresent()){
            productRepo.delete(product.get());
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Product Not Found / Invalid ProductID", HttpStatus.CONFLICT);
    }
}
