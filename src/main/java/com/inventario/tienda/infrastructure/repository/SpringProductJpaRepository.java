package com.inventario.tienda.infrastructure.repository;

import com.inventario.tienda.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringProductJpaRepository extends JpaRepository<ProductEntity, Long> {
}
