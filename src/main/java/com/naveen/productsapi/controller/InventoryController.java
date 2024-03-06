package com.naveen.productsapi.controller;

import com.naveen.productsapi.dto.InventoryRequest;
import com.naveen.productsapi.model.Inventory;
import com.naveen.productsapi.service.InventoryService;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllProductsFromInventory(){
        logger.debug("entered get of inventory list");
        return new ResponseEntity<List<Inventory>>(inventoryService.getAllProductsFromInventory(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addProductToInventory(@RequestBody InventoryRequest inventoryRequest){
        return inventoryService.addProductToInventory(inventoryRequest);
    }

    @DeleteMapping("/{pid}")
    public ResponseEntity<String> deleteProductFromInventory(@PathVariable Long pid){
        return inventoryService.deleteProductFromInventory(pid);
    }


    @PutMapping("{iid}")
    public ResponseEntity<?> updateProductInInventory(@RequestBody InventoryRequest inventoryRequest, @PathVariable Long iid) {
        return inventoryService.updateProductInInventory(inventoryRequest, iid);
    }

    @PostMapping("/check")
    public ResponseEntity<?> checkInventory(@RequestBody List<InventoryRequest> inventoryRequests) {
        return inventoryService.checkForProduct(inventoryRequests);
    }

}
