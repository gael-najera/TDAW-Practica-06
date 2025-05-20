package com.upiiz.Practica_6.controllers;

import com.upiiz.Practica_6.models.Product;
import com.upiiz.Practica_6.services.ProductService;
import com.upiiz.Practica_6.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("productos", productService.findAll());
        return "listado_producto";
    }

    @GetMapping("/products/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categorias", categoryService.findAll());
        return "agregar_producto";
    }

    @PostMapping("/products/guardar")
    public String guardarProducto(@ModelAttribute Product producto) {
        productService.save(producto);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("categorias", categoryService.findAll());
        return "editar_producto";
    }

    @PostMapping("/products/edit")
    public String editProducto(@ModelAttribute Product product) {
        productService.update(product);
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "eliminar_producto";
    }

    @PostMapping("/products/delete")
    public String deleteProduct(@ModelAttribute Product product) {
        productService.delete(product.getProductId());
        return "redirect:/products";
    }
}

