package com.naveen.productsapi.DTO;


import com.naveen.productsapi.model.Inventory;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
@Builder
@Component
public class ProductQuantity {
    private String productId;
    private String skuCode;
    private String productName;
    private BigDecimal price;
    private String gender;
    private String size;
    private String colour;
    private String type;
    private Integer availableQuantity;
    private Boolean isPresentInInventory;
}
