package com.final_project.final_project.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table()
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long payment_id;

    private Long client_id;
    private Double total_paid;
    private LocalDate date;

    public Payment() {}
    public Payment(Long payment_id, Long client_id, Double total_paid, LocalDate date) {
        this.payment_id = payment_id;
        this.client_id = client_id;
        this.total_paid = total_paid;
        this.date = date;
    }

    public Long getPayment_id() {
        return payment_id;
    }
    public void setPayment_id(Long payment_id) {
        this.payment_id = payment_id;
    }

    public Long getClient_id() {
        return client_id;
    }
    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public Double getTotal_paid() {
        return total_paid;
    }
    public void setTotal_paid(Double total_paid) {
        this.total_paid = total_paid;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
