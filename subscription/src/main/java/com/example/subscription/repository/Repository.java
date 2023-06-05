package com.example.subscription.repository;

import com.example.subscription.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Subscription,Long> {
    @Query("SELECT s FROM Subscription s WHERE s.customerId = ?1")
    List<Subscription> getSubscriptionByCustomerId(String customerId);
}
