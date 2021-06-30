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
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 이름(U)
    @Column(unique = true)
    private String name;

    // 소개
    private String description;

    // 가격
    private Integer price;

    // 이미지 주소
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
