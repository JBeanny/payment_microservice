package com.example.subscription.controller;

import com.example.subscription.model.Subscription;
import com.example.subscription.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class Controller {

    @Autowired
    private Service service;

    @GetMapping()
    public ResponseEntity<Object> getSubscriptions(){
        Map<String,Object> response = new HashMap<>();

        try{
            List<Subscription> subcriptions = service.getSubscriptions();
            response.put("status","success");
            response.put("result",subcriptions.size());
            response.put("subscription",subcriptions);

            return ResponseEntity.status(201).body(response);
        }catch(Exception e) {
            response.put("status","fail");
            response.put("message",e.getMessage());

            return ResponseEntity.status(400).body(response);
        }
    }

    @PostMapping()
    public ResponseEntity<Object> uploadSubscription(@RequestBody Subscription subscription){
        Map<String,Object> response = new HashMap<>();

        try{
            Subscription createdSubscription = service.save(subscription);
            response.put("status","success");
            response.put("message","created successfully");
            response.put("subscription",createdSubscription);

            return ResponseEntity.status(201).body(response);
        }catch(Exception e) {
            response.put("status","fail");
            response.put("message",e.getMessage());

            return ResponseEntity.status(400).body(response);
        }
    }
}
