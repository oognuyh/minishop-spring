package com.example.minishop_spring.repository;

import com.example.minishop_spring.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
