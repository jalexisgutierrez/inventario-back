package com.inventario.tienda.domain.service;

import com.inventario.tienda.domain.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductDomainService {

    public void validateStock(Product product, int quantity) {
        if (product.getStock() < quantity) {
            throw new IllegalArgumentException("No hay stock suficiente");
        }
    }

    public void decreaseStock(Product product, int quantity) {
        product.setStock(product.getStock() - quantity);
    }

    public void increaseStock(Product product, int quantity) {
        product.setStock(product.getStock() + quantity);
    }
}
