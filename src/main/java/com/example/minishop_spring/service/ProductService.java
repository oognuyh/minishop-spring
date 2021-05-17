package com.example.minishop_spring.service;

import com.example.minishop_spring.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> fetchProductsByCategoryId(Integer categoryId);
    Optional<Product> fetchProductById(Integer id);
}
