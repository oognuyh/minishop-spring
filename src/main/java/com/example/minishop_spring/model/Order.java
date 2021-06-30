package com.example.minishop_spring.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
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
    private String paymentMethod;

    // 청구지 이름
    @Column(name = "billing_name")
    private String billingName;

    // 청구지 전화번호
    @Column(name = "billing_phone_number")
    private String billingPhoneNumber;

    // 청구지 우편번호
    @Column(name = "billing_zip")
    private String billingZip;

    // 청구지 주소 1
    @Column(name = "billing_address1")
    private String billingAddress1;

    // 청구지 주소 2
    @Column(name = "billing_address2")
    private String billingAddress2;

    // 수령지 이름
    @Column(name = "shipping_name")
    private String shippingName;

    // 수령지 전화번호
    @Column(name = "shipping_phone_number")
    private String shippingPhoneNumber;

    // 수령지 우편번호
    @Column(name = "shipping_zip")
    private String shippingZip;

    // 수령지 주소 1
    @Column(name = "shipping_address1")
    private String shippingAddress1;

    // 수령지 주소 2
    @Column(name = "shipping_address2")
    private String shippingAddress2;

    // 총액
    @Column(name = "total_price")
    private Integer totalPrice;

    // 주문 상세
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    @JsonManagedReference
    private List<OrderDetail> orderDetails;
}
