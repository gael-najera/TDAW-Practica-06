package com.upiiz.Practica_6.repositories;

import com.upiiz.Practica_6.models.ProductCategory;
import java.util.List;

public interface ProductCategoryRepository {
    // Todas las relaciones producto-categoría
    List<ProductCategory> findAll();

    // Relaciones por producto
    List<ProductCategory> findByProductId(int productId);

    // Relaciones por categoría
    List<ProductCategory> findByCategoryId(int categoryId);

    // Agregar una relación
    void save(ProductCategory productCategory);

    // Eliminar una relación específica
    void delete(int productId, int categoryId);
}
