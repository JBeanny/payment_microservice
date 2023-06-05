package com.example.payment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "total_price")
    private Long totalPrice;

    @Column(name = "total_subscription")
    private Integer totalSubscription;

    @Column(name = "expire_date")
    private LocalDateTime expireDate;

    @Column(name = "message")
    private String message;

}
