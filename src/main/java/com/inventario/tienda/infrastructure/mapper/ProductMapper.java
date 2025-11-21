package com.inventario.tienda.infrastructure.mapper;

import com.inventario.tienda.domain.model.Product;
import com.inventario.tienda.domain.model.ProductType;
import com.inventario.tienda.infrastructure.entity.ProductEntity;

public final class ProductMapper {

    public static Product toDomain(ProductEntity e) {
        if (e == null) return null;
        ProductType type = e.getType() != null ? ProductType.valueOf(e.getType()) : null;
        return Product.builder()
                .id(e.getId())
                .name(e.getName())
                .type(type)
                .stock(e.getStock())
                .minStock(e.getMinStock())
                .basePrice(e.getBasePrice())
                .build();
    }

    public static ProductEntity toEntity(Product p) {
        if (p == null) return null;
        return ProductEntity.builder()
                .id(p.getId())
                .name(p.getName())
                .type(p.getType() != null ? p.getType().name() : null)
                .stock(p.getStock())
                .minStock(p.getMinStock())
                .basePrice(p.getBasePrice())
                .build();
    }
}
