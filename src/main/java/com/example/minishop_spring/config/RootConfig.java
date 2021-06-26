package com.example.minishop_spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

@Configuration
@Import({ DatabaseConfig.class })
@ComponentScan(
        basePackages = "com.example.minishop_spring",
        excludeFilters = {
                @Filter(
                        type = FilterType.ANNOTATION,
                        classes = { Controller.class }
                )
        }
)
public class RootConfig {
}
