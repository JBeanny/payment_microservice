package com.example.subscription.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private Long subscriptionId;

    @Column(name = "bundle_id")
    private Long bundleId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "expire_date")
    private LocalDateTime expireDate;

    @Column(name = "subscribe_date")
    private LocalDateTime subscribeDate;
}

