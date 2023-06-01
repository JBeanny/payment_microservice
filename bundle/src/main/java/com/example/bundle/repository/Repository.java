package com.example.bundle.repository;

import com.example.bundle.model.Bundle;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Bundle,Long> {
}
