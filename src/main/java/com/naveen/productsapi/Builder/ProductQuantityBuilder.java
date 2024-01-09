package com.naveen.productsapi.Builder;


import com.naveen.productsapi.DTO.ProductQuantity;
import com.naveen.productsapi.model.Inventory;
import com.naveen.productsapi.model.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Data
@Component
public class ProductQuantityBuilder {
    @Autowired
    ProductQuantity productQuantity;

    public ProductQuantity buildProductQuantity(Inventory inventory){
        Product product = inventory.getProduct();
        productQuantity = ProductQuantity.builder()
                .productId(String.valueOf(product.getProductId()))
                .skuCode(product.getSkuCode())
                .productName(product.getProductName())
                .price(product.getPrice())
                .gender(product.getGender())
                .size(product.getSize())
                .colour(product.getColour())
                .type(product.getType())
                .availableQuantity(inventory.getQuantity())
                .build();
        return productQuantity;
    }
}
