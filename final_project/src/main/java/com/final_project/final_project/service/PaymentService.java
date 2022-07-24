package com.final_project.final_project.service;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.final_project.final_project.entity.Payment;
import com.final_project.final_project.repository.PaymentRepository;

@Service
public class PaymentService {
    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getPayment() {
        return paymentRepository.findAll();
    }

    public Payment findById(Long payment_id) {
        return this.paymentRepository
                    .findById(payment_id)
                    .orElseThrow(() -> new EntityNotFoundException("Client Not Found"));
    }

    public void create(Payment toAdd) {
        paymentRepository.save(toAdd);
    }


    @Bean
    CommandLineRunner commandLineRunner(PaymentRepository paymentRepository) {
        return args -> {
            Payment payment = new Payment(
                (long) 1,
                (long) 1,
                123.1,
                LocalDate.now()
            );
            paymentRepository.saveAll(List.of(payment));
        };
    }
}
