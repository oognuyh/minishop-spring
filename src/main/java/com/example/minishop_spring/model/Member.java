package com.example.minishop_spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
    public interface DetailsValidator {}

    // 고유번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 이름
    @NotBlank(message = "이름을 입력해주세요.", groups = DetailsValidator.class)
    private String name;

    // 이메일(U)
    @Column(unique = true)
    @Email(message = "이메일 형식이 올바르지 않습니다.", groups = DetailsValidator.class)
    private String email;

    // 비밀번호
    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    private String password;

    // 전화번호
    @Column(name = "phone_number", unique = true)
    @NotBlank(message = "전화번호를 입력하세요.", groups = DetailsValidator.class)
    private String phoneNumber;

    // 우편번호
    @NotBlank(message = "우편번호를 입력하세요.", groups = DetailsValidator.class)
    private String zip;

    // 주소 1
    @NotBlank(message = "주소를 입력하세요.", groups = DetailsValidator.class)
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
