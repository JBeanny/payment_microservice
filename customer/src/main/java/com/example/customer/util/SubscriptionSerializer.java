package com.example.customer.util;

import com.example.customer.model.Subscription;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class SubscriptionSerializer {

    public Subscription serialize(String jsonString) throws JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();
        // add this module to ObjectMapper because by default Java 8 not support LocalDateTime
        obj.registerModule(new JavaTimeModule());
        String str = obj.readTree(jsonString).get("subscription").toString();

        return obj.readValue(str,Subscription.class);
    }
}
