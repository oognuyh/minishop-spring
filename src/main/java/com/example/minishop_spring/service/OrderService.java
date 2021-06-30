package com.example.minishop_spring.service;

import com.example.minishop_spring.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> findOrdersByMemberId(Integer memberId);
    void order(Order order);
}
