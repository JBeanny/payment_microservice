package com.example.payment.util;

import com.example.payment.model.Subscription;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class SubscriptionSerializer {

    public Subscription[] serialize(String jsonString) throws JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();
        obj.registerModule(new JavaTimeModule());
        String str = obj.readTree(jsonString).get("subscription").toString();

        return obj.readValue(str,Subscription[].class);
    }

    public Integer bundleDurationSerializer(String duration) {
        String[] bundleDuration = duration.split("d");
        return Integer.valueOf(bundleDuration[0]);
    }
}
