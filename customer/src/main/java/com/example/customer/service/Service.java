package com.example.customer.service;

import com.example.customer.model.Bundle;
import com.example.customer.model.Customer;
import com.example.customer.model.Subscription;
import com.example.customer.repository.Repository;
import com.example.customer.util.BundleSerializer;
import com.example.customer.util.CustomerIdDeserializer;
import com.example.customer.util.Helper;
import com.example.customer.util.SubscriptionSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Repository repository;

    public Customer save(Customer customer){
        customer.setCustomerId(new CustomerIdDeserializer().format(customer.getCustomerId()));
        repository.save(customer);
        return customer;
    }

    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    public Map<String,Object> uploadSubscription(Subscription request) throws JsonProcessingException {
        Map<String,Object> logger = new HashMap<>();

        // build url and call to bundle service
        String bundleUrl = "http://localhost:8081/api/v1/bundles/" + request.getBundleId();
        String apiResponse = new Helper().callApiToRetrieve(bundleUrl);

        // convert to Bundle format
        Bundle bundle = new BundleSerializer().serialize(apiResponse);
        // bundle duration
        Integer bundleDuration = new BundleSerializer().bundleDurationSerializer(bundle.getBundleDuration());

        // build payload
        Subscription subscription = Subscription.builder()
                .bundleId(bundle.getBundleId())
                .customerId(request.getCustomerId())
                .expireDate(LocalDateTime.now().plusDays(bundleDuration))
                .subscribeDate(LocalDateTime.now())
                .build();

        // url for subcription service
        String subscriptionUrl = "http://localhost:8083/api/v1/subscriptions";
        String subscriptionApiResponse = new Helper().callApiToPost(subscriptionUrl,subscription);

        // convert to Subscript format
        Subscription createdSubscription = new SubscriptionSerializer().serialize(subscriptionApiResponse);

        logger.put("subscription",createdSubscription);
        logger.put("bundle",bundle);

        return logger;
    }
}
