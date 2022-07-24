package com.final_project.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.final_project.final_project.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
}
