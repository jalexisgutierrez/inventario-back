package com.inventario.tienda.domain.repository;

import com.inventario.tienda.domain.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository {
    Sale save(Sale sale);
    List<Sale> findAll();
}
