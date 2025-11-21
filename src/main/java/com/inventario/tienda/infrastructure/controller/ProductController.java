package com.inventario.tienda.infrastructure.controller;

import com.inventario.tienda.application.dto.MetricsResponse;
import com.inventario.tienda.application.dto.OrderRequest;
import com.inventario.tienda.application.dto.SellRequest;
import com.inventario.tienda.application.usecase.GetMetricsUseCase;
import com.inventario.tienda.application.usecase.OrderProductUseCase;
import com.inventario.tienda.application.usecase.SellProductUseCase;
import com.inventario.tienda.domain.model.Product;
import com.inventario.tienda.domain.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductRepository productRepository;
    private final SellProductUseCase sellUseCase;
    private final OrderProductUseCase orderUseCase;
    private final GetMetricsUseCase metricsUseCase;

    @GetMapping
    public ResponseEntity<List<Product>> listAll() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @PostMapping("/{id}/sell")
    public ResponseEntity<Void> sell(@PathVariable Long id, @RequestBody @Valid SellRequest req) {
        sellUseCase.execute(id, req.getQuantity());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/order")
    public ResponseEntity<Void> order(@PathVariable Long id, @RequestBody @Valid OrderRequest req) {
        orderUseCase.execute(id, req.getQuantity());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/metrics")
    public ResponseEntity<MetricsResponse> metrics() {
        return ResponseEntity.ok(metricsUseCase.execute());
    }
}
