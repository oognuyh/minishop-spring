package com.example.minishop_spring.controller;

import com.example.minishop_spring.annotation.CurrentMember;
import com.example.minishop_spring.model.Cart;
import com.example.minishop_spring.model.Member;
import com.example.minishop_spring.model.Response;
import com.example.minishop_spring.service.CartService;
import com.example.minishop_spring.util.ObjectUtil;
import com.example.minishop_spring.util.ValidatorUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/auth/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("/list")
    public ModelAndView cart(@CurrentMember Member member) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView("cart/cart");

        modelAndView.addObject("carts", ObjectUtil.asString(cartService.findCartsByMemberId(member.getId())));

        return modelAndView;
    }

    @PostMapping
    @ResponseBody
    public Response<?> insert(@RequestBody @Valid Cart cart,
                              BindingResult bindingResult,
                              @CurrentMember Member member) {
        log.info("cart: {}", cart);
        if (bindingResult.hasErrors())
            return Response.ERROR(ValidatorUtil.getErrorsAsObject(bindingResult));


        cart.setMemberId(member.getId());
        cartService.insert(cart);

        return Response.OK();
    }

    @PutMapping
    @ResponseBody
    public Response<?> update(@RequestBody Cart cart) {
        log.info("{}", cart);

        cartService.update(cart);

        return Response.OK();
    }

    @DeleteMapping
    @ResponseBody
    public Response<?> delete(@RequestBody Cart cart) {
        log.info("{}", cart);

        cartService.delete(cart);

        return Response.OK();
    }
}
