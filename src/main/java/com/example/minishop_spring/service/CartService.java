package com.example.minishop_spring.service;

import com.example.minishop_spring.model.Cart;

import java.util.List;

public interface CartService {

    List<Cart> findCartsByMemberId(Integer memberId);
    void update(Cart cart);
    void insert(Cart cart);
    void delete(Cart cart);
}