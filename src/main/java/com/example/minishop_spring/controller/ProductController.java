package com.example.minishop_spring.controller;

import com.example.minishop_spring.exception.ProductDoesNotExistException;
import com.example.minishop_spring.model.Product;
import com.example.minishop_spring.service.ProductService;
import com.example.minishop_spring.util.ObjectUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ModelAndView showProducts(@RequestParam(defaultValue = "1") int categoryId) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView("product/list");

        List<Product> products = productService.fetchProductsByCategoryId(categoryId);
        modelAndView.addObject("products", ObjectUtil.asString(products));

        return modelAndView;
    }

    @GetMapping("/detail/{productId}")
    public ModelAndView showProductDetails(@PathVariable("productId") int productId) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView("product/detail");

        Product product = productService.fetchProductById(productId).orElseThrow(ProductDoesNotExistException::new);
        modelAndView.addObject("product", ObjectUtil.asString(product));

        return modelAndView;
    }
}
