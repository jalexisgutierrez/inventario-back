package com.inventario.tienda.domain.service;

import com.inventario.tienda.domain.model.Product;
import com.inventario.tienda.domain.model.Sale;
import com.inventario.tienda.domain.repository.ProductRepository;
import com.inventario.tienda.domain.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetricsService {

    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final ProductDomainService productDomainService;

    public void execute(Long id, int quantity) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        productDomainService.validateStock(product, quantity);
        productDomainService.decreaseStock(product, quantity);

        double totalPrice = product.getFinalPrice() * quantity;

        saleRepository.save(
                Sale.builder()
                        .productId(id)
                        .quantity(quantity)
                        .totalAmount(totalPrice)
                        .build()
        );

        productRepository.save(product);
    }
}
