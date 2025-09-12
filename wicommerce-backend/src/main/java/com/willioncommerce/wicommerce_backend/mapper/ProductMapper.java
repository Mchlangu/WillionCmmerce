package com.willioncommerce.wicommerce_backend.mapper;

import com.willioncommerce.wicommerce_backend.dto.ProductDto;
import com.willioncommerce.wicommerce_backend.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product entity);
    Product toEntity(ProductDto dto);
}
