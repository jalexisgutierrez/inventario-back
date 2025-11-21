package com.inventario.tienda.application.usecase;

import com.inventario.tienda.domain.model.Product;
import com.inventario.tienda.domain.model.Sale;
import com.inventario.tienda.domain.repository.ProductRepository;
import com.inventario.tienda.domain.repository.SaleRepository;
import com.inventario.tienda.domain.service.ProductDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SellProductUseCase {

    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;

    public void execute(Long productId, int quantity) {
        Product p = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        if (p.getStock() == null || p.getStock() < quantity) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Insufficient stock");
        }

        // update product stock
        p.setStock(p.getStock() - quantity);

        // optionally update soldUnits and revenue in product domain (not strictly necessary)
        p.setSoldUnits((p.getSoldUnits() == null ? 0 : p.getSoldUnits()) + quantity);
        double totalAmount = p.getFinalPrice() * quantity;
        p.setTotalRevenue((p.getTotalRevenue() == null ? 0.0 : p.getTotalRevenue()) + totalAmount);

        productRepository.save(p);

        // create sale record
        Sale sale = Sale.builder()
                .productId(productId)
                .quantity(quantity)
                .totalAmount(totalAmount)
                .saleDate(LocalDateTime.now())
                .build();
        saleRepository.save(sale);
    }
}
