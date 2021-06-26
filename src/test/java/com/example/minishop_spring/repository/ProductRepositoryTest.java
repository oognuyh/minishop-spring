package com.example.minishop_spring.repository;

import com.example.minishop_spring.config.RootConfig;
import com.example.minishop_spring.config.WebConfig;
import com.example.minishop_spring.model.Category;
import com.example.minishop_spring.model.Product;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RootConfig.class, WebConfig.class })
class ProductRepositoryTest {
    @Setter(onMethod_ = @Autowired)
    private ProductRepository productRepository;

    @DisplayName("상품 전체 목록")
    @Test
    public void testFindAll() {
        log.info("{}", productRepository.findAll());
    }

    @DisplayName("해당 카테고리 상품 목록")
    @Test
    public void testFindByCategoryId() {
        productRepository.findByCategoryIdOrderById(1).forEach(product -> log.info("{}", product));
    }

    @DisplayName("상품 추가")
    @Test
    public void testSaveProduct() {
        Product product = Product.builder()
                .name("test")
                .price(500)
                .image("test")
                .category(new Category(1, "outer"))
                .description("test")
                .build();

        log.info("saved: {}", productRepository.save(product));
    }

    @DisplayName("상품 수정")
    @Test
    public void testUpdateProduct() {

    }
}