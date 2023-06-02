package com.example.customer.controller;

import com.example.customer.model.Customer;
import com.example.customer.model.Subscription;
import com.example.customer.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/customers")
public class Controller {

    @Autowired
    private Service service;

    @GetMapping()
    public ResponseEntity<Object> getCustomers() {
        Map<String,Object> response = new HashMap<>();

        try{
            List<Customer> customers = service.getCustomers();
            response.put("status","success");
            response.put("result",customers.size());
            response.put("customers",customers);

            return ResponseEntity.status(200).body(response);
        }catch(Exception e){
            response.put("status","fail");
            response.put("message",e.getMessage());

            return ResponseEntity.status(400).body(response);
        }
    }

    @PostMapping()
    public ResponseEntity<Object> uploadCustomer(@RequestBody Customer customer) {
        Map<String,Object> response = new HashMap<>();

        try{
            Customer createdCustomer = service.save(customer);
            response.put("status","success");
            response.put("message","created successfully");
            response.put("customer",createdCustomer);

            return ResponseEntity.status(201).body(response);

        }catch(Exception e) {
            response.put("status","fail");
            response.put("message",e.getMessage());

            return ResponseEntity.status(400).body(response);
        }
    }

    @PostMapping("/bundles")
    public ResponseEntity<Object> subscribe(@RequestBody Subscription request){
        Map<String,Object> response = new HashMap<>();

        try{
            Map<String,Object> apiResponse = service.uploadSubscription(request);
            response.put("status","success");
            response.put("message","subscribed successfully");
            response.put("data",apiResponse);

            return ResponseEntity.status(201).body(response);

        }catch(Exception e) {
            response.put("status","fail");
            response.put("message",e.getMessage());

            return ResponseEntity.status(400).body(response);
        }
    }

}
