package com.example.bundle.controller;

import com.example.bundle.model.Bundle;
import com.example.bundle.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/bundles")
public class Controller {

    @Autowired
    private Service service;
    @GetMapping()
    public ResponseEntity<Object> getBundles() {
        System.out.println("hello");
        Map<String,Object> response = new HashMap<>();

        try{
            List<Bundle> bundles = service.getBundles();
            response.put("status","success");
            response.put("result",bundles.size());
            response.put("bundles",bundles);

            return ResponseEntity.ok(response);
        }catch(Exception e) {
            response.put("status","fail");
            response.put("message",e.getMessage());

            return ResponseEntity.status(400).body(response);
        }
    }
}
