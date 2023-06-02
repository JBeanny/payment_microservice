package com.example.subscription.repository;

import com.example.subscription.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Subscription,Long> {
}
