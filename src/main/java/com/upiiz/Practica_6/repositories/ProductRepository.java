package com.upiiz.Practica_6.repositories;

import com.upiiz.Practica_6.models.Product;
import java.util.List;

public interface ProductRepository {
    // Todos los productos
    List<Product> findAll();

    // Un solo producto por su id
    Product findById(int id);

    // Agregar un producto
    void save(Product product);

    // Actualizar un producto
    void update(Product product);

    // Eliminar un producto
    void delete(int id);
}