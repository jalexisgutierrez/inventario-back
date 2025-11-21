package com.inventario.tienda.application.usecase;

import com.inventario.tienda.domain.model.Product;
import com.inventario.tienda.domain.repository.ProductRepository;
import com.inventario.tienda.domain.service.ProductDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class OrderProductUseCase {

    private final ProductRepository productRepository;

    public void execute(Long productId, int quantity) {
        Product p = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        p.setStock((p.getStock() == null ? 0 : p.getStock()) + quantity);
        productRepository.save(p);
    }
}
