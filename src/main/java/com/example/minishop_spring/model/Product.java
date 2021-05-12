package com.example.minishop_spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    // 이름(U)
    @Column(name = "name")
    private String name;

    // 카테고리 고유번호
    @Column(name = "category_id")
    private Integer categoryId;

    // 소개
    @Column(name = "description")
    private String description;

    // 가격
    @Column(name = "price")
    private Integer price;

    // 이미지 주소
    @Column(name = "image")
    private String image;

    @OneToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;
}
