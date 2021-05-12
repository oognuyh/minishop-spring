package com.example.minishop_spring.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString(exclude = {"orders", "carts"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {
    // 고유번호
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // 이름
    @Column(name = "name")
    private String name;

    // 이메일(U)
    @Column(name = "email")
    private String email;

    // 비밀번호
    @Column(name = "password")
    private String password;

    // 전화번호
    @Column(name = "phone_number")
    private String phoneNumber;

    // 우편번호
    @Column(name = "zip")
    private String zip;

    // 주소 1
    @Column(name = "address1")
    private String address1;

    // 주소 2
    @Column(name = "address2")
    private String address2;

    // 생성일자
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 수정일자
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 주문
    @OneToMany
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private List<Order> orders;

    // 장바구니
    @OneToMany
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private List<Cart> carts;
}
