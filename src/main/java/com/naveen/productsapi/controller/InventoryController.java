package com.naveen.productsapi.controller;

import com.naveen.productsapi.model.Inventory;
import com.naveen.productsapi.service.InventoryService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllProductsFromInventory(){
        return new ResponseEntity<List<Inventory>>(inventoryService.getAllProductsFromInventory(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Inventory> addProductToInventory(@RequestBody Inventory productStats){
        return new ResponseEntity<Inventory>(inventoryService.addProductToInventory(productStats),HttpStatus.CREATED);
    }

    @DeleteMapping("/{pid}")
    public ResponseEntity<String> deleteProductFromInventory(@PathVariable Long pid){
        return inventoryService.deleteProductFromInventory(pid);
    }

    @PutMapping("{pid}")
    public ResponseEntity<?> updateProductInInventory(@PathVariable Long pid, @RequestBody Inventory inventory){
        return inventoryService.updateProductInInventory(pid,inventory);
    }




}
