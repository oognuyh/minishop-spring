package com.example.minishop_spring.service;

import com.example.minishop_spring.model.Order;
import com.example.minishop_spring.repository.CartRepository;
import com.example.minishop_spring.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@AllArgsConstructor
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    @Override
    public List<Order> findOrdersByMemberId(Integer memberId) {
        return orderRepository.findByMemberIdOrderByIdDesc(memberId);
    }

    @Override
    public void order(Order order) {
        order.getOrderDetails().forEach(orderDetail -> orderDetail.setOrder(order));

        orderRepository.save(order);

        order.getOrderDetails().forEach(orderDetail ->
                cartRepository
                        .findByOrderDetailAndMemberId(orderDetail, order.getMemberId())
                        .ifPresent(cartRepository::delete));
    }
}