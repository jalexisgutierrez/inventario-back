package com.inventario.tienda.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor @Builder
public class MetricsResponse {

    private Long mostSoldProductId;
    private Long leastSoldProductId;
    private Double totalMoney;
    private Double averageSales;
}
