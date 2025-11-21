package com.inventario.tienda.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale {

    private Long id;
    private Long productId;
    private Integer quantity;
    private Double totalAmount;
    private LocalDateTime saleDate;
}
