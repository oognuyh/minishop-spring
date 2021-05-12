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
@Table(name = "order_detail")
public class OrderDetail {
    // 고유번호
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    // 주문 고유번호
    @Column(name = "order_id")
    private Integer orderId;

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

    // 총액
    @Column(name = "total_price")
    private Integer totalPrice;

    // 상품
    @OneToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;
}
