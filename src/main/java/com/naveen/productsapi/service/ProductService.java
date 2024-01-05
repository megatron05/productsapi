package com.naveen.productsapi.service;

import com.naveen.productsapi.dto.InventoryRequest;
import com.naveen.productsapi.dto.ProductRequest;
import com.naveen.productsapi.model.Inventory;
import com.naveen.productsapi.model.Model;
import com.naveen.productsapi.model.Product;
import com.naveen.productsapi.repository.InventoryRepo;
import com.naveen.productsapi.repository.ModelRepo;
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
    private final ModelRepo modelRepo;
    public ResponseEntity<List<Product>> getAllProducts() {
        return  new ResponseEntity<>(productRepo.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<?> getProduct(Long pid) {
        if(productRepo.findById(pid).isPresent()){
            return new ResponseEntity<>(productRepo.findById(pid).get(), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("Product not found  or Invalid product id", HttpStatus.CONFLICT);
        }
    }

//    public Product addProduct(Product product) { return productRepo.save(product); }

    public ResponseEntity<?> addProduct(ProductRequest productRequest){
        Optional<Model> model = modelRepo.findById(productRequest.getModelId());
        if(model.isPresent()){
            try{
                Product product = Product.builder()
                        .productName(productRequest.getProductName())
                        .model(model.get())
                        .size(productRequest.getSize())
                        .colour(productRequest.getColour())
                        .price(productRequest.getPrice())
                        .build();

                Product pro = productRepo.save(product);
                return new ResponseEntity<>(pro, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>("Product already exits", HttpStatus.CONFLICT);
            }

        }
        return new ResponseEntity<>("Model not found or Invalid model id", HttpStatus.CONFLICT);
    }

    public ResponseEntity<?> addProductToInventory(InventoryRequest inventoryRequest) {
        Optional<Product> product = productRepo.findById(inventoryRequest.getProductId());
        if(product.isPresent()) {
            Optional<Inventory> inventory = inventoryRepo.findByProduct(product.get());
            if(inventory.isPresent()){
                return new ResponseEntity<>("Already exits in inventory", HttpStatus.CONFLICT);
            }
            else{
                Inventory inv = new Inventory();
                inv.setProduct(product.get());
                inv.setQuantity(inventoryRequest.getQuantity());
                return new ResponseEntity<>(inventoryRepo.save(inv),HttpStatus.CREATED);

            }

        }
        return new ResponseEntity<>("Invalid Product Id", HttpStatus.CONFLICT);
    }

    public ResponseEntity<?> deleteProduct(Long pid) {
        Optional<Product> product = productRepo.findById(pid);
        if(product.isPresent()){
            productRepo.delete(product.get());
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Product Not Found / Invalid ProductID", HttpStatus.CONFLICT);
    }


    public void updateProduct(ProductRequest productRequest, Long productId) {
    }

//    To Map productRequest to product object

//    private Product mapToProduct(ProductRequest productRequest, Long pId) {
//        return Product.builder()
//                .productId(pId)
//                .productName(productRequest.getProductName())
//                .size(productRequest.getSize())
//                .colour(productRequest.getColour())
//                .build();
//    }
}
