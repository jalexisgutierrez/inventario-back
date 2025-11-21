-- ============================================
--   CREACIÓN DE TABLAS
-- ============================================

DROP TABLE IF EXISTS sales;
DROP TABLE IF EXISTS products;

-- ============================================
--   TABLA products
-- ============================================
CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    type VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    min_quantity INT NOT NULL,
    base_price NUMERIC(10,2) NOT NULL,
    units_sold INT NOT NULL DEFAULT 0
);

-- ============================================
--   TABLA sales
-- ============================================
CREATE TABLE sales (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    total_amount NUMERIC(12,2) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products(id)
);

-- ============================================
--   DATA SEMILLA (20 PRODUCTOS)
-- ============================================

INSERT INTO products (name, type, quantity, min_quantity, base_price, units_sold)
VALUES
('Lápiz HB', 'PAPELERIA', 50, 10, 10.00, 0),
('Cuaderno rayado', 'PAPELERIA', 40, 8, 2500.00, 0),
('Marcador negro', 'PAPELERIA', 30, 5, 3500.00, 0),
('Bolígrafo azul', 'PAPELERIA', 60, 15, 1200.00, 0),
('Carpeta tamaño carta', 'PAPELERIA', 25, 5, 1800.00, 0),

('Leche entera 1L', 'SUPERMERCADO', 80, 20, 3800.00, 0),
('Arroz 1kg', 'SUPERMERCADO', 120, 30, 2900.00, 0),
('Huevos docena', 'SUPERMERCADO', 50, 12, 7500.00, 0),
('Azúcar 1kg', 'SUPERMERCADO', 100, 25, 2700.00, 0),
('Aceite vegetal 1L', 'SUPERMERCADO', 60, 15, 6200.00, 0),

('Acetaminofén 500mg', 'DROGUERIA', 40, 10, 1500.00, 0),
('Ibuprofeno 400mg', 'DROGUERIA', 35, 10, 1800.00, 0),
('Alcohol antiséptico 70%', 'DROGUERIA', 20, 5, 3500.00, 0),
('Algodón 100g', 'DROGUERIA', 15, 4, 2500.00, 0),
('Gasas estériles x10', 'DROGUERIA', 25, 5, 3000.00, 0),

('Galletas de chocolate', 'SUPERMERCADO', 90, 25, 3200.00, 0),
('Jabón líquido manos', 'SUPERMERCADO', 45, 8, 4500.00, 0),
('Tijeras escolares', 'PAPELERIA', 20, 5, 2900.00, 0),
('Resaltador amarillo', 'PAPELERIA', 50, 10, 2500.00, 0),
('Pan tajado', 'SUPERMERCADO', 70, 20, 5200.00, 0);

-- ============================================
--   VENTAS INICIALES (para estadísticas)
-- ============================================

INSERT INTO sales (product_id, quantity, total_amount)
VALUES
(1, 5, 58.00),
(2, 3, 8700.00),
(6, 10, 39500.00),
(11, 4, 6720.00);

-- Actualizar los units_sold coherentemente
UPDATE products SET units_sold = 5 WHERE id = 1;
UPDATE products SET units_sold = 3 WHERE id = 2;
UPDATE products SET units_sold = 10 WHERE id = 6;
UPDATE products SET units_sold = 4 WHERE id = 11;

