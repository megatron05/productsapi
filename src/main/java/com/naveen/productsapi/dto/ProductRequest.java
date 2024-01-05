package com.naveen.productsapi.dto;

import com.naveen.productsapi.model.Model;
import io.micrometer.observation.ObservationFilter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
