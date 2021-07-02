package com.example.minishop_spring.controller;

import com.example.minishop_spring.annotation.CurrentMember;
import com.example.minishop_spring.exception.CanNotCheckOutWithoutProductsException;
import com.example.minishop_spring.model.*;
import com.example.minishop_spring.service.OrderService;
import com.example.minishop_spring.util.ObjectUtil;
import com.example.minishop_spring.util.ValidatorUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/auth/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/history")
    public ModelAndView showOrderHistory(@CurrentMember Member member) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView("order/history");

        List<Order> orders = orderService.findOrdersByMemberId(member.getId());
        modelAndView.addObject("orders", ObjectUtil.asString(orders));

        return modelAndView;
    }

    @GetMapping("/checkout")
    public ModelAndView showOrderCheckout(HttpSession session) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView("order/checkout");

        modelAndView.addObject("order", ObjectUtil.asString(session.getAttribute("order")));

        return modelAndView;
    }

    @PostMapping("/before-checkout")
    @ResponseBody
    public Response<?> insertIntoSession(HttpSession session,
                                         @CurrentMember Member member,
                                         @RequestBody List<Cart> carts) {
        if (carts.isEmpty()) throw new CanNotCheckOutWithoutProductsException();

        List<OrderDetail> orderDetails = carts.stream()
                .map(Cart::toOrderDetail)
                .collect(Collectors.toList());

        Order order = Order.builder()
                .memberId(member.getId())
                .billingName(member.getName())
                .billingPhoneNumber(member.getPhoneNumber())
                .billingZip(member.getZip())
                .billingAddress1(member.getAddress1())
                .billingAddress2(member.getAddress2())
                .paymentMethod("credit")
                .orderDetails(orderDetails)
                .totalPrice(orderDetails.stream()
                        .mapToInt(OrderDetail::getTotalPrice).sum())
                .build();

        log.info("{}", order);

        session.setAttribute("order", order);

        return Response.OK();
    }

    @PostMapping
    @ResponseBody
    public Response<?> insert(HttpSession session,
                              @RequestBody @Valid Order order,
                              BindingResult bindingResult) {
        log.info("{}", order);
        if (bindingResult.hasErrors()) return Response.ERROR(ValidatorUtil.getErrorsAsObject(bindingResult));

        orderService.order(order);
        session.removeAttribute("order");

        return Response.OK("주문이 완료되었습니다.");
    }
}
