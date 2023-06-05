package com.example.payment.util;

import com.example.payment.model.Bundle;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.cglib.core.Local;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Helper {

    WebClient webClient = WebClient.create();
    public String callApiToRetrieve(String apiUrl){
        return webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public Integer getBundlePrice(Long bundle_id) throws JsonProcessingException {
        String bundleUrl = "http://localhost:8081/api/v1/bundles/" + bundle_id;
        String apiResponse = new Helper().callApiToRetrieve(bundleUrl);

        Bundle bundle = new BundleSerializer().serialize(apiResponse);

        return bundle.getBundlePrice();
    }

    public LocalDateTime getNextWeekLocalDateTime(){
        return LocalDateTime.now().plusDays(7);
    }
}
