package com.example.minishop_spring.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_history")
@ToString(exclude = { "orderDetails" })
public class Order {
    // 고유번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 사용자 고유번호
    @Column(name = "member_id")
    private Integer memberId;

    // 결제수단
    @Column(name = "payment_method")
    @NotBlank(message = "결제수단을 선택하세요.")
    private String paymentMethod;

    // 청구지 이름
    @Column(name = "billing_name")
    @NotBlank(message = "청구인 이름을 입력하세요.")
    private String billingName;

    // 청구지 전화번호
    @Column(name = "billing_phone_number")
    @NotBlank(message = "청구인 전화번호를 입력하세요.")
    private String billingPhoneNumber;

    // 청구지 우편번호
    @Column(name = "billing_zip")
    @NotBlank(message = "청구지 우편번호를 입력하세요.")
    private String billingZip;

    // 청구지 주소 1
    @Column(name = "billing_address1")
    @NotBlank(message = "청구지 주소를 입력하세요.")
    private String billingAddress1;

    // 청구지 주소 2
    @Column(name = "billing_address2")
    private String billingAddress2;

    // 수령지 이름
    @Column(name = "shipping_name")
    @NotBlank(message = "수령인 이름을 입력하세요.")
    private String shippingName;

    // 수령지 전화번호
    @Column(name = "shipping_phone_number")
    @NotBlank(message = "수령인 전화번호를 입력하세요.")
    private String shippingPhoneNumber;

    // 수령지 우편번호
    @Column(name = "shipping_zip")
    @NotBlank(message = "수령지 우편번호를 입력하세요.")
    private String shippingZip;

    // 수령지 주소 1
    @Column(name = "shipping_address1")
    @NotBlank(message = "수령지 주소를 입력하세요.")
    private String shippingAddress1;

    // 수령지 주소 2
    @Column(name = "shipping_address2")
    private String shippingAddress2;

    // 총액
    @Column(name = "total_price")
    @Positive(message = "총액이 0원 이하일 수 없습니다.")
    private Integer totalPrice;

    // 주문 상세
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    @JsonManagedReference
    @Valid
    private List<OrderDetail> orderDetails;
}
