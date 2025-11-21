package com.inventario.tienda.infrastructure.mapper;

import com.inventario.tienda.domain.model.Sale;
import com.inventario.tienda.infrastructure.entity.ProductEntity;
import com.inventario.tienda.infrastructure.entity.SaleEntity;

import java.time.LocalDateTime;

public final class SaleMapper {

    public static Sale toDomain(SaleEntity e) {
        if (e == null) return null;
        return Sale.builder()
                .id(e.getId())
                .productId(e.getProduct() != null ? e.getProduct().getId() : null)
                .quantity(e.getQuantity())
                .totalAmount(e.getTotalAmount())
                .saleDate(e.getSaleDate())
                .build();
    }

    public static SaleEntity toEntity(Sale s, ProductEntity productEntity) {
        if (s == null) return null;
        return SaleEntity.builder()
                .id(s.getId())
                .product(productEntity)
                .quantity(s.getQuantity())
                .totalAmount(s.getTotalAmount())
                .saleDate(s.getSaleDate() != null ? s.getSaleDate() : LocalDateTime.now())
                .build();
    }
}
