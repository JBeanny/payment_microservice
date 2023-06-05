package com.example.payment.controller;

import com.example.payment.model.Payment;
import com.example.payment.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payments")
public class Controller {

    @Autowired
    private Service service;

    @GetMapping()
    public ResponseEntity<Object> getPayments() {
        Map<String,Object> response = new HashMap<>();

        try{
            List<Payment> payments = service.getPayments();
            response.put("status","success");
            response.put("result",payments.size());
            response.put("payments",payments);

            return ResponseEntity.status(201).body(response);
        }catch(Exception e) {
            response.put("status","fail");
            response.put("message",e.getMessage());

            return ResponseEntity.status(400).body(response);
        }
    }

    @GetMapping("monthly_payment/{customer_id}")
    public ResponseEntity<Object> getMonthlyPaymentByCustomerId(@PathVariable String customer_id) {
        Map<String,Object> response = new HashMap<>();

        try{
            Payment payment = service.getMonthlyPaymentByCustomerId(customer_id);
            response.put("status","success");
            response.put("payment",payment);

            return ResponseEntity.status(201).body(response);
        }catch(Exception e) {
            response.put("status","fail");
            response.put("message",e.getMessage());

            return ResponseEntity.status(400).body(response);
        }
    }

    @PostMapping()
    public ResponseEntity<Object> uploadPayment(@RequestBody Payment payment){
        Map<String,Object> response = new HashMap<>();

        try{
            Payment createdPayment = service.save(payment);
            response.put("status","success");
            response.put("message","created successfully");
            response.put("payment",createdPayment);

            return ResponseEntity.status(201).body(response);
        }catch(Exception e) {
            response.put("status","fail");
            response.put("message",e.getMessage());

            return ResponseEntity.status(400).body(response);
        }
    }
}
