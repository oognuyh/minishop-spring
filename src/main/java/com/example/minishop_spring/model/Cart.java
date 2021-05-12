package com.example.minishop_spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart {

    @Id
    private Integer id;

    @Column(name = "member_id")
    private Integer memberId;

    // 상품 고유번호
    @Column(name = "product_id")
    private Integer productId;

    // 상품 수량
    @Column(name = "product_quantity")
    private Integer productQuantity;

    // 상품 색상
    @Column(name = "product_color")
    private String productColor;

    // 상품 사이즈
    @Column(name = "product_size")
    private String productSize;

    @OneToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;
}
