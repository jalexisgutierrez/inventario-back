package com.inventario.tienda.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellRequest {
    @NotNull
    @Min(1)
    private Integer quantity;
}
