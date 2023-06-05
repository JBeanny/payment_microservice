package com.example.subscription.service;

import com.example.subscription.model.Subscription;
import com.example.subscription.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Repository repository;

    public List<Subscription> getSubscriptions(){
        return repository.findAll();
    }

    public Subscription save(Subscription subscription){

        Subscription subscriptionToSave = Subscription.builder()
                .bundleId(subscription.getBundleId())
                .customerId(subscription.getCustomerId())
                .expireDate(subscription.getExpireDate())
                .subscribeDate(subscription.getSubscribeDate())
                .build();
        repository.save(subscriptionToSave);

        return subscriptionToSave;
    }

    public List<Subscription> getCustomerSubscription(String customerId) {
        return repository.getSubscriptionByCustomerId(customerId);
    }
}
