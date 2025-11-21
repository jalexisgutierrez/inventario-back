package com.inventario.tienda.domain.repository;

import com.inventario.tienda.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(Long id);
    List<Product> findAll();
    Product save(Product product);
}
