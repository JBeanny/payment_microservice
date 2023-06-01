package com.example.bundle.controller;

import com.example.bundle.model.Bundle;
import com.example.bundle.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    
    @PostMapping()
    public ResponseEntity<Object> uploadBundle(@RequestBody Bundle bundle) {
        Map<String,Object> response = new HashMap<>();
        
        try{
            Bundle createdBundle = service.uploadBundle(bundle);
            response.put("status","success");
            response.put("message","created successfully");
            response.put("bundle",bundle);
            
            return ResponseEntity.status(201).body(response);
        }catch(Exception e) {
            response.put("status","fail");
            response.put("message", e.getMessage());
            
            return ResponseEntity.status(400).body(response);
        }
    }
}
