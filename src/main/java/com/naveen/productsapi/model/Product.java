package com.naveen.productsapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.json.JSONObject;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "model_id", referencedColumnName = "modelId")
    private Model model;
    private String size;
    private String colour;
    private BigDecimal price;


}
