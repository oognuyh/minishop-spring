package com.example.minishop_spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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
@Table(name = "member")
public class Member {
    // 고유번호
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_gen")
    @SequenceGenerator(
            name = "member_seq_gen",
            sequenceName = "member_seq",
            allocationSize = 1
    )
    @Column(name = "id")
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
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 수정일자
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "updated_at")
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
