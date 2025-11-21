package com.inventario.tienda.infrastructure.repository;

import com.inventario.tienda.domain.model.Sale;
import com.inventario.tienda.domain.repository.SaleRepository;
import com.inventario.tienda.infrastructure.entity.ProductEntity;
import com.inventario.tienda.infrastructure.entity.SaleEntity;
import com.inventario.tienda.infrastructure.mapper.SaleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SaleRepositoryAdapter implements SaleRepository {

    private final SpringSaleJpaRepository jpa;
    private final SpringProductJpaRepository productJpa;

    @Override
    public Sale save(Sale sale) {
        ProductEntity productEntity = productJpa.findById(sale.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found for sale"));
        SaleEntity entity = SaleMapper.toEntity(sale, productEntity);
        SaleEntity saved = jpa.save(entity);
        return SaleMapper.toDomain(saved);
    }

    @Override
    public List<Sale> findAll() {
        return jpa.findAll().stream().map(SaleMapper::toDomain).collect(Collectors.toList());
    }
}
