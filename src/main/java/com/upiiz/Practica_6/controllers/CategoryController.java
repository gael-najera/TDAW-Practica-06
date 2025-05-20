package com.upiiz.Practica_6.controllers;

import com.upiiz.Practica_6.models.Category;
import com.upiiz.Practica_6.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Ver todas las categorías
    @GetMapping("/categories")
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "listado_categorias";  // Vista para mostrar todas las categorías
    }

    // Crear categoría
    @GetMapping("/categories/new")
    public String newCategory(Model model) {
        model.addAttribute("category", new Category());
        return "agregar_categoria";  // Vista para crear una nueva categoría
    }

    @PostMapping("/categories/guardar")
    public String createCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }

    // Editar categoría
    @GetMapping("/categories/edit/{id}")
    public String editCategory(@PathVariable int id, Model model) {
        Category category = categoryService.findById(id);
        System.out.println("ID: " + category.getCategoryId());
        System.out.println("Nombre: " + category.getName());
        System.out.println("Descripción: " + category.getDescription());
        model.addAttribute("category", category);
        return "editar_categoria";
    }


    @PostMapping("/categories/editar")
    public String updateCategory(@ModelAttribute Category category) {
        categoryService.update(category);
        return "redirect:/categories";
    }

    // Eliminar categoría
    @GetMapping("/categories/delete/{id}")
    public String formDeleteCategory(@PathVariable int id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "eliminar_categoria";
    }

    @PostMapping("/categories/eliminar")
    public String deleteCategory(@RequestParam int categoryId) {
        categoryService.delete(categoryId);
        return "redirect:/categories";
    }



}

