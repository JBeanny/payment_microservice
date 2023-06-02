package com.example.payment.service;

import com.example.payment.model.Payment;
import com.example.payment.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Repository repository;

    public List<Payment> getPayments(){
        return repository.findAll();
    }

    public Payment save(Payment payment) {
        // get next week date from the currentDate
        final LocalDateTime nextWeek = LocalDateTime.now()
                .with(TemporalAdjusters.next(LocalDateTime.now().getDayOfWeek()));

        Payment paymentToSave = Payment.builder()
                .customerId(payment.getCustomerId())
                .totalPrice(payment.getTotalPrice())
                .totalSubscription(payment.getTotalSubscription())
                .subscriptionId(payment.getSubscriptionId())
                .expireDate(nextWeek)
                .message("You must pay the bill before " + nextWeek.toLocalDate())
                .build();
        repository.save(paymentToSave);
        return paymentToSave;
    }
}
