package com.inventario.tienda.infrastructure.repository;

import com.inventario.tienda.domain.model.Product;
import com.inventario.tienda.domain.repository.ProductRepository;
import com.inventario.tienda.infrastructure.entity.ProductEntity;
import com.inventario.tienda.infrastructure.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {

    private final SpringProductJpaRepository jpa;

    @Override
    public Optional<Product> findById(Long id) {
        return jpa.findById(id).map(ProductMapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return jpa.findAll().stream().map(ProductMapper::toDomain).toList();
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = ProductMapper.toEntity(product);
        ProductEntity saved = jpa.save(entity);
        return ProductMapper.toDomain(saved);
    }
}
