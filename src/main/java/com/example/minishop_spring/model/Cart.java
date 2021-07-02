package com.example.minishop_spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

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
    @Positive(message = "상품 수량은 1개 이상이어야 합니다.")
    private Integer productQuantity;

    // 상품 색상
    @Column(name = "product_color")
    @NotBlank(message = "상품 색상을 선택해야합니다.")
    private String productColor;

    // 상품 사이즈
    @Column(name = "product_size")
    @NotBlank(message = "상품 사이즈를 선택해야합니다.")
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
