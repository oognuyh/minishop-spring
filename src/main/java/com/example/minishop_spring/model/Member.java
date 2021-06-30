package com.example.minishop_spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
@ToString(exclude = {"orders", "carts"})
public class Member {
    // 고유번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 이름
    private String name;

    // 이메일(U)
    @Column(unique = true)
    private String email;

    // 비밀번호
    private String password;

    // 전화번호
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    // 우편번호
    private String zip;

    // 주소 1
    private String address1;

    // 주소 2
    private String address2;

    // 권한
    private String authority;

    // 생성일자
    @Column(name = "created_at")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    // 수정일자
    @Column(name = "updated_at")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedAt;

    // 주문
    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "id")
    private List<Order> orders;

    // 장바구니
    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "id")
    private List<Cart> carts;

    @PrePersist
    private void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
