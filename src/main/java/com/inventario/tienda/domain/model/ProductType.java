package com.inventario.tienda.domain.model;

public enum ProductType {

    PAPELERIA(0.16),
    SUPERMERCADO(0.04),
    DROGUERIA(0.12);

    private final double tax;

    ProductType(double tax) { this.tax = tax; }
    public double getTax() { return tax; }
}
