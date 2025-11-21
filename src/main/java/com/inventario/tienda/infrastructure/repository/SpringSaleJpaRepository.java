package com.inventario.tienda.infrastructure.repository;

import com.inventario.tienda.infrastructure.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringSaleJpaRepository extends JpaRepository<SaleEntity, Long> {
}
