package com.willioncommerce.wicommerce_backend.service;

import com.willioncommerce.wicommerce_backend.dto.ProductDto;
import com.willioncommerce.wicommerce_backend.entity.Product;
import com.willioncommerce.wicommerce_backend.mapper.ProductMapper;
import com.willioncommerce.wicommerce_backend.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductServiceImpl(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto dto) {
        Product newProduct = mapper.toEntity(dto);
        newProduct = repository.save(newProduct);
        return mapper.toDto(newProduct);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDto> getProductById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public ProductDto updateProduct(Long id, ProductDto dto) {
        Product existingProduct = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        Product updatedProduct = mapper.toEntity(dto);
        updatedProduct.setId(id);

        if (updatedProduct.getStockQuantity() == null) {
            updatedProduct.setStockQuantity(0);
        }

        updatedProduct = repository.save(updatedProduct);
        return mapper.toDto(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        repository.deleteById(id);
    }


}

