package com.willioncommerce.wicommerce_backend.service;

import com.willioncommerce.wicommerce_backend.dto.ProductDto;
import com.willioncommerce.wicommerce_backend.entity.Product;
import com.willioncommerce.wicommerce_backend.exception.ResourceNotFoundException;
import com.willioncommerce.wicommerce_backend.mapper.ProductMapper;
import com.willioncommerce.wicommerce_backend.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Transactional(readOnly = true)
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        Page<Product> productPage = repository.findAll(pageable);
        return productPage.map(mapper::toDto);
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
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        Product updatedProduct = mapper.toEntity(dto);
        updatedProduct.setId(id);

        updatedProduct = repository.save(updatedProduct);
        return mapper.toDto(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        repository.deleteById(id);
    }


}

