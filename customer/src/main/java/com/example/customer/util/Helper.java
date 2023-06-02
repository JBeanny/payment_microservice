package com.example.customer.util;

import com.example.customer.model.Subscription;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

public class Helper {

    WebClient webClient = WebClient.create();
    public String callApiToRetrieve(String apiUrl){
        return webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String callApiToPost(String apiUrl, Subscription payload){
        return webClient.post()
                .uri(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(payload)
                .exchangeToMono(cr -> cr.bodyToMono(String.class))
                .timeout(Duration.ofMillis(10000))
                .block();
    }
}
