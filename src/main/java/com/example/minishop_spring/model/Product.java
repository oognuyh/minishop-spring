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
public class Product {
    // 고유번호
    private int id;

    // 이름(U)
    private String name;

    // 카테고리 고유번호
    private int categoryId;

    // 소개
    private String description;

    // 가격
    private int price;

    // 이미지 주소
    private String image;

    // 카테고리 이름
    private String categoryName;
}
