package com.example.minishop_spring.repository;

import com.example.minishop_spring.model.Cart;
import com.example.minishop_spring.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("select c from Cart c " +
           "where c.memberId = :#{#cart.memberId} and " +
           "c.productColor = :#{#cart.productColor} and " +
           "c.productSize = :#{#cart.productSize}")
    Optional<Cart> existsAlready(@Param("cart") Cart cart);
    List<Cart> findByMemberId(int memberId);
    @Query("select cart from Cart cart " +
           "where cart.memberId = :memberId and " +
           "cart.product = :#{#orderDetail.product} and " +
           "cart.productSize = :#{#orderDetail.productSize} and " +
           "cart.productColor = :#{#orderDetail.productColor} and " +
           "cart.productQuantity = :#{#orderDetail.productQuantity}")
    Optional<Cart> findByOrderDetailAndMemberId(@Param("orderDetail") OrderDetail orderDetail, @Param("memberId") Integer memberId);
}
