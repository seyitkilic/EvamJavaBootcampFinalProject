package com.final_project.final_project.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table()
public class Invoice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long invoice_id;

    private Long client_id;

    private Double debt;
    private LocalDate date;

    private boolean status = false;

    
    public Invoice() {}

    public Invoice(Long invoice_id, Long client_id, Double debt, LocalDate date) {
        this.invoice_id = invoice_id;
        this.client_id = client_id;
        this.debt = debt;
        this.date = date;
    }
    
    public Long getInvoice_id() {
        return invoice_id;
    }
    public void setInvoice_id(Long invoice_id) {
        this.invoice_id = invoice_id;
    }
    public Long getClient_id() {
        return client_id;
    }
    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }
    public Double getDebt() {
        return debt;
    }
    public void setDebt(Double debt) {
        this.debt = debt;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return invoice_id.equals(invoice.invoice_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoice_id);
    }
}
