package com.final_project.final_project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.final_project.final_project.entity.Payment;
import com.final_project.final_project.service.PaymentService;

@RestController
@RequestMapping("/final/payment")
@RequestScope
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/get")
    public List<Payment> getAll() {
        return this.paymentService.getPayment();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("payment_id") Long payment_id){
        try {
            return ResponseEntity.ok(this.paymentService.findById(payment_id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> add(@RequestBody Payment payment) {
        this.paymentService.create(payment);
        return ResponseEntity.ok("Payment Eklendi");
    }
}
