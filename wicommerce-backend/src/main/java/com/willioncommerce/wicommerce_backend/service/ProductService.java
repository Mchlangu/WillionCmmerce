package com.willioncommerce.wicommerce_backend.service;

import com.willioncommerce.wicommerce_backend.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDto createProduct(ProductDto dto);
    Page<ProductDto> getAllProducts(Pageable pageable);
    Optional<ProductDto> getProductById(Long id);
    ProductDto updateProduct(Long id, ProductDto dto);
    void deleteProduct(Long id);

}
