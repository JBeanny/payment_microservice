package com.example.bundle.service;

import com.example.bundle.model.Bundle;
import com.example.bundle.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Repository repository;
    public List<Bundle> getBundles() {
        return repository.findAll();
    }
}
