package com.example.minishop_spring.controller;

import com.example.minishop_spring.model.Product;
import com.example.minishop_spring.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(@Autowired ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public ModelAndView findProducts(@RequestParam(defaultValue = "1") int categoryId) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> products = productService.fetchProductsByCategoryId(categoryId);

        modelAndView.setViewName("product/list");
        modelAndView.addObject("products", new ObjectMapper().writeValueAsString(products));

        return modelAndView;
    }

    @GetMapping("/detail")
    public ModelAndView findProductById(@RequestParam int productId) throws IllegalAccessException, JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView();
        Product product = productService.fetchProductById(productId).orElseThrow(IllegalAccessException::new);

        modelAndView.setViewName("product/detail");
        modelAndView.addObject("product", new ObjectMapper().writeValueAsString(product));

        return modelAndView;
    }
}
