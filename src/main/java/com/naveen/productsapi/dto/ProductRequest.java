package com.naveen.productsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private Long modelId;
    private String productName;
    private String size;
    private String colour;
    private BigDecimal price;

}
