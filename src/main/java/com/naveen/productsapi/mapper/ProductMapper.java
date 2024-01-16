package com.naveen.productsapi.mapper;

import com.naveen.productsapi.dto.ProductRequest;
import com.naveen.productsapi.model.Model;
import com.naveen.productsapi.model.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

public class ProductMapper {
    private  static final ModelMapper modelMapper = new ModelMapper();
    public static Product mapProductRequestToProduct(ProductRequest productRequest, Model model, Long productId) {
        Product product = modelMapper.map(productRequest, Product.class);
        // Set the provided Model object for the Product
        product.setProductId(productId);
        product.setModel(model);
        return product;
    }
}

