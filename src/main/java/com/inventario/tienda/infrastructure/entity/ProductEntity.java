package com.inventario.tienda.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "type")
    private String type;

    private Integer stock;

    @Column(name = "min_stock")
    private Integer minStock;

    @Column(name = "base_price")
    private Double basePrice;
}
