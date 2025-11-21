package com.inventario.tienda.application.usecase;

import com.inventario.tienda.application.dto.MetricsResponse;
import com.inventario.tienda.domain.model.Sale;
import com.inventario.tienda.domain.repository.ProductRepository;
import com.inventario.tienda.domain.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetMetricsUseCase {

    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;

    public MetricsResponse execute() {
        List<Sale> sales = saleRepository.findAll();
        if (sales.isEmpty()) {
            return new MetricsResponse(null, null, 0.0, 0.0);
        }

        // aggregate units sold by product id
        Map<Long, Integer> unitsByProduct = new HashMap<>();
        for (Sale s : sales) {
            unitsByProduct.merge(s.getProductId(), s.getQuantity(), Integer::sum);
        }

        Optional<Map.Entry<Long,Integer>> most = unitsByProduct.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        Optional<Map.Entry<Long,Integer>> least = unitsByProduct.entrySet().stream()
                .min(Map.Entry.comparingByValue());

        double totalRevenue = sales.stream().mapToDouble(Sale::getTotalAmount).sum();
        int totalUnits = sales.stream().mapToInt(Sale::getQuantity).sum();
        double average = totalUnits == 0 ? 0.0 : totalRevenue / totalUnits;

        return new MetricsResponse(
                most.map(Map.Entry::getKey).orElse(null),
                least.map(Map.Entry::getKey).orElse(null),
                totalRevenue,
                average
        );
    }
}
