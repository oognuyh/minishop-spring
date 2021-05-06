package com.example.minishop_spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Cart {
    // 고유번호
    private int id;

    // 사용자 고유번호
    private int memberId;

    // 상품 고유번호
    private int productId;

    // 상품 수량
    private int productQuantity;

    // 상품 색상
    private String productColor;

    // 상품 사이즈
    private String productSize;

    // 상품
    private Product product;
}
