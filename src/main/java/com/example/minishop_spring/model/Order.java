package com.example.minishop_spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Order {
    // 고유번호
    private int id;

    // 사용자 고유번호
    private int memberId;

    // 결제수단
    private String paymentMethod;

    // 청구지 이름
    private String billingName;

    // 청구지 전화번호
    private String billingPhoneNumber;

    // 청구지 우편번호
    private String billingZip;

    // 청구지 주소 1
    private String billingAddress1;

    // 청구지 주소 2
    private String billingAddress2;

    // 수령지 이름
    private String shippingName;

    // 수령지 전화번호
    private String shippingPhoneNumber;

    // 수령지 우편번호
    private String shippingZip;

    // 수령지 주소 1
    private String shippingAddress1;

    // 수령지 주소 2
    private String shippingAddress2;

    // 총액
    private int totalPrice;

    // 주문 상세
    private List<OrderDetail> orderDetails;
}
