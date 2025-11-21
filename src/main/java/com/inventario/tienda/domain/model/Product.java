package com.inventario.tienda.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private Long id;
    private String name;
    private ProductType type;
    private Integer stock;
    private Integer minStock;
    private Double basePrice;
    private Integer soldUnits;
    private Double totalRevenue;

    public double getFinalPrice() {
        if (basePrice == null || type == null) return 0.0;
        return basePrice + (basePrice * type.getTax());
    }

    public boolean needsReorder() {
        return stock != null && minStock != null && stock <= minStock;
    }
}
