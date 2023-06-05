package com.example.payment.model;

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
public class Subscription {

    private Long subscriptionId;

    private Long bundleId;

    private String customerId;

    private LocalDateTime expireDate;

    private LocalDateTime subscribeDate;
}

