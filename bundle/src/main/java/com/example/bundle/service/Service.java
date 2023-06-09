package com.example.bundle.service;

import com.example.bundle.model.Bundle;
import com.example.bundle.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Repository repository;
    public List<Bundle> getBundles() {
        return repository.findAll();
    }
    
    public Bundle uploadBundle(Bundle bundle) {
        repository.save(bundle);
        return bundle;
    }

    public Optional<Bundle> getBundle(Long bundleId) {
        return repository.findById(bundleId);
    }
}
