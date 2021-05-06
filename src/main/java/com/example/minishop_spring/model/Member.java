package com.example.minishop_spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Member {
    // 고유번호
    private int id;

    // 이름
    private String name;

    // 이메일(U)
    private String email;

    // 비밀번호
    private String password;

    // 전화번호
    private String phoneNumber;

    // 우편번호
    private String zip;

    // 주소 1
    private String address1;

    // 주소 2
    private String address2;

    // 생성일자
    private LocalDateTime createdAt;

    // 수정일자
    private LocalDateTime updatedAt;

    // 주문
    private List<Order> orders;

    // 장바구니
    private List<Cart> carts;
}
