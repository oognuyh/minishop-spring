package com.example.minishop_spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "member_id")
    private Integer memberId;

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
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderDetail toOrderDetail() {
        return OrderDetail.builder()
                .productQuantity(productQuantity)
                .productColor(productColor)
                .productSize(productSize)
                .product(product)
                .totalPrice(getProduct().getPrice() * productQuantity)
                .build();
    }
}
