package com.example.minishop_spring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "order_detail")
public class OrderDetail {
    // 고유번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @JsonBackReference
    private Order order;

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
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
