package com.example.sales.repository;

import com.example.sales.model.entities.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransmissionRepository extends JpaRepository<Transmission, Long> {
}
