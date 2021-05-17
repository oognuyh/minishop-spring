package com.example.minishop_spring.service;

import com.example.minishop_spring.model.Product;
import com.example.minishop_spring.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@AllArgsConstructor
@Service("productService")
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> fetchProductsByCategoryId(Integer categoryId) {
        return productRepository.findByCategoryIdOrderById(categoryId);
    }

    @Override
    public Optional<Product> fetchProductById(Integer id) {
        return productRepository.findById(id);
    }
}
