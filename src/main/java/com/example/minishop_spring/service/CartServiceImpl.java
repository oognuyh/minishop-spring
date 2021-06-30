package com.example.minishop_spring.service;

import com.example.minishop_spring.exception.ProductDoesNotExistException;
import com.example.minishop_spring.model.Cart;
import com.example.minishop_spring.repository.CartRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@AllArgsConstructor
@Service("cartService")
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Override
    public List<Cart> findCartsByMemberId(Integer memberId) {
        return cartRepository.findByMemberId(memberId);
    }

    @Override
    public void update(Cart cart) {
        Cart existingCart = cartRepository.findById(cart.getId()).orElseThrow(ProductDoesNotExistException::new);

        existingCart.setProductQuantity(cart.getProductQuantity());

        cartRepository.save(existingCart);
    }

    @Override
    public void insert(Cart cart) {
        Optional<Cart> existingCartWrapper = cartRepository.existsAlready(cart);


        if (existingCartWrapper.isPresent()) {
            Cart existingCart = existingCartWrapper.get();

            existingCart.setProductQuantity(cart.getProductQuantity() + existingCart.getProductQuantity());

            cartRepository.save(existingCart);
        } else {
            cartRepository.save(cart);
        }
    }

    @Override
    public void delete(Cart cart) {
        cartRepository.delete(cart);
    }
}
