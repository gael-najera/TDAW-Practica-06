package com.upiiz.Practica_6.repositories;

import com.upiiz.Practica_6.models.Category;
import java.util.List;

public interface CategoryRepository {
    // Todas las categorías
    List<Category> findAll();

    // Una sola categoría por su id
    Category findById(int id);

    // Agregar una categoría
    void save(Category category);

    // Actualizar una categoría
    void update(Category category);

    // Eliminar una categoría
    void delete(int id);
}
