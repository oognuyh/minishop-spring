package com.example.minishop_spring.repository;

import com.example.minishop_spring.config.RootConfig;
import com.example.minishop_spring.config.WebConfig;
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
class OrderDetailRepositoryTest {
    @Setter(onMethod_ = @Autowired)
    private OrderDetailRepository orderDetailRepository;

    @DisplayName("주문상세 전체 목록")
    @Test
    public void testFindAll() {
        log.info("{}", orderDetailRepository.findAll());
    }
}