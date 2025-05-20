package com.upiiz.Practica_6.services;

import com.upiiz.Practica_6.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Product> findAll() {
        String sql = "SELECT p.id_producto as productId, p.nombre as name, p.precio as price, " +
                "p.stock as stock, c.id_categoria as categoryId, c.nombre as categoryName " +
                "FROM productos p " +
                "LEFT JOIN productos_categorias pc ON p.id_producto = pc.id_producto " +
                "LEFT JOIN categorias c ON pc.id_categoria = c.id_categoria";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }

    public Product findById(int id) {
        String sql = "SELECT p.id_producto as productId, p.nombre as name, p.precio as price, " +
                "p.stock as stock, c.id_categoria as categoryId, c.nombre as categoryName " +
                "FROM productos p " +
                "LEFT JOIN productos_categorias pc ON p.id_producto = pc.id_producto " +
                "LEFT JOIN categorias c ON pc.id_categoria = c.id_categoria " +
                "WHERE p.id_producto = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class), id)
                .stream().findFirst().orElse(null);
    }

    public void save(Product product) {
        // Insertar producto
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO productos(nombre, precio, stock) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setBoolean(3, product.isStock());
            return ps;
        }, keyHolder);

        // Insertar relación con categoría si existe
        if(product.getCategoryId() != null) {
            int productId = keyHolder.getKey().intValue();
            jdbcTemplate.update(
                    "INSERT INTO productos_categorias(id_producto, id_categoria) VALUES (?, ?)",
                    productId, product.getCategoryId()
            );
        }
    }

    public void update(Product product) {
        // Actualizar producto
        jdbcTemplate.update(
                "UPDATE productos SET nombre = ?, precio = ?, stock = ? WHERE id_producto = ?",
                product.getName(), product.getPrice(), product.isStock(), product.getProductId()
        );

        // Actualizar relación con categoría
        jdbcTemplate.update(
                "DELETE FROM productos_categorias WHERE id_producto = ?",
                product.getProductId()
        );

        if(product.getCategoryId() != null) {
            jdbcTemplate.update(
                    "INSERT INTO productos_categorias(id_producto, id_categoria) VALUES (?, ?)",
                    product.getProductId(), product.getCategoryId()
            );
        }
    }

    public void delete(int id) {
        // Primero eliminar la relación con categorías
        jdbcTemplate.update("DELETE FROM productos_categorias WHERE id_producto = ?", id);
        // Luego eliminar el producto
        jdbcTemplate.update("DELETE FROM productos WHERE id_producto = ?", id);
    }
}