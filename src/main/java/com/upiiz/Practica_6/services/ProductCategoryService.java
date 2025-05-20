package com.upiiz.Practica_6.services;

import com.upiiz.Practica_6.models.ProductCategory;
import com.upiiz.Practica_6.repositories.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService implements ProductCategoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ProductCategory> findAll() {
        return jdbcTemplate.query("SELECT * FROM productos_categorias",
                (rs, rowNum) -> {
                    ProductCategory pc = new ProductCategory();
                    pc.setProductId(rs.getInt("id_producto"));
                    pc.setCategoryId(rs.getInt("id_categoria"));
                    return pc;
                });
    }

    @Override
    public List<ProductCategory> findByProductId(int productId) {
        return jdbcTemplate.query("SELECT * FROM productos_categorias WHERE id_producto = ?",
                (rs, rowNum) -> {
                    ProductCategory pc = new ProductCategory();
                    pc.setProductId(rs.getInt("id_producto"));
                    pc.setCategoryId(rs.getInt("id_categoria"));
                    return pc;
                }, productId);
    }

    @Override
    public List<ProductCategory> findByCategoryId(int categoryId) {
        return jdbcTemplate.query("SELECT * FROM productos_categorias WHERE id_categoria = ?",
                (rs, rowNum) -> {
                    ProductCategory pc = new ProductCategory();
                    pc.setProductId(rs.getInt("id_producto"));
                    pc.setCategoryId(rs.getInt("id_categoria"));
                    return pc;
                }, categoryId);
    }

    @Override
    public void save(ProductCategory productCategory) {
        jdbcTemplate.update("INSERT INTO productos_categorias(id_producto, id_categoria) VALUES (?, ?)",
                productCategory.getProductId(), productCategory.getCategoryId());
    }

    @Override
    public void delete(int productId, int categoryId) {
        jdbcTemplate.update("DELETE FROM productos_categorias WHERE id_producto = ? AND id_categoria = ?",
                productId, categoryId);
    }
}