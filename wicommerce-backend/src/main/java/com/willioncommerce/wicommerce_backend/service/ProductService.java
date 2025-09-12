package com.willioncommerce.wicommerce_backend.service;

import com.willioncommerce.wicommerce_backend.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDto createProduct(ProductDto dto);
    List<ProductDto> getAllProducts();
    Optional<ProductDto> getProductById(Long id);
    ProductDto updateProduct(Long id, ProductDto dto);
    void deleteProduct(Long id);

}
