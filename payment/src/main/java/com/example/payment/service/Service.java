package com.example.payment.service;

import com.example.payment.model.Payment;
import com.example.payment.model.Subscription;
import com.example.payment.repository.Repository;
import com.example.payment.util.Helper;
import com.example.payment.util.SubscriptionSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
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
                .expireDate(nextWeek)
                .message("You must pay the bill before " + nextWeek.toLocalDate())
                .build();
        repository.save(paymentToSave);
        return paymentToSave;
    }

    public Payment getMonthlyPaymentByCustomerId(String customerId) throws JsonProcessingException {
        // subscription uri
        String subscriptionUrl = "http://localhost:8083/api/v1/subscriptions/" + customerId;
        String apiResponse = new Helper().callApiToRetrieve(subscriptionUrl);

        // monthly subscription getting from the api call
        Subscription[] monthlySubscriptions = new SubscriptionSerializer().serialize(apiResponse);

        // getting total price and total subscriptions
        Long totalPrice = 0L;
        Integer totalSub = 0;

        for(int i=0;i<monthlySubscriptions.length;i++) {
            totalSub++;
            // getting price from the bundle
            totalPrice += new Helper().getBundlePrice(monthlySubscriptions[i].getBundleId());
        }

        LocalDateTime expireDate = new Helper().getNextWeekLocalDateTime();

         Payment payment = Payment.builder()
                .customerId(customerId)
                .totalSubscription(totalSub)
                .totalPrice(totalPrice)
                .expireDate(expireDate)
                .message("You must pay the bill before " + expireDate.toLocalDate())
                .build();

         payment = repository.save(payment);

         return payment;
    }
}
