package com.upiiz.Practica_6.services;

import com.upiiz.Practica_6.models.Category;
import com.upiiz.Practica_6.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements CategoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Category> findAll() {
        return jdbcTemplate.query("SELECT id_categoria AS categoryId, nombre AS name, descripcion AS description FROM categorias",
                new BeanPropertyRowMapper<>(Category.class));
    }

    @Override
    public Category findById(int id) {
        return jdbcTemplate.query(
                "SELECT id_categoria AS categoryId, nombre AS name, descripcion AS description FROM categorias WHERE id_categoria = ?",
                new BeanPropertyRowMapper<>(Category.class),
                id
        ).stream().findFirst().orElse(new Category());
    }



    @Override
    public void save(Category category) {
        jdbcTemplate.update("INSERT INTO categorias(nombre, descripcion) VALUES (?, ?)",
                category.getName(), category.getDescription());
    }

    @Override
    public void update(Category category) {
        jdbcTemplate.update("UPDATE categorias SET nombre = ?, descripcion = ? WHERE id_categoria = ?",
                category.getName(), category.getDescription(), category.getCategoryId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM categorias WHERE id_categoria = ?", id);
    }
}