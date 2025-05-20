package com.upiiz.Practica_6.models;

public class Product {
    private int productId;
    private String name;
    private double price;
    private boolean stock;
    private Integer categoryId;  // Usamos Integer en lugar de int
    private String categoryName;

    public Product() {
    }

    public Product(int productId, String name, double price, boolean stock, Integer categoryId, String categoryName) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    // Getters y Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }

    // CORRECCIÓN IMPORTANTE: Cambiar el tipo de retorno a Integer
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

