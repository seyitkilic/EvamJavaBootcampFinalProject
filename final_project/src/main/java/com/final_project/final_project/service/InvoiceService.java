package com.final_project.final_project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.final_project.final_project.entity.Invoice;
import com.final_project.final_project.repository.InvoiceRepository;


@Service
public class InvoiceService {
    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getInvoices() {
        return invoiceRepository.findAll();
    }

    public void create(Invoice toAdd){
        invoiceRepository.save(toAdd);
    }

    public void delete(Long invoice_id) {
        invoiceRepository.deleteById(invoice_id);
    }

    public Invoice findById(Long invoice_id) {
        return this.invoiceRepository
                    .findById(invoice_id)
                    .orElseThrow(() -> new EntityNotFoundException("Invoice Not Found"));
    }

    public Long getInvoiceStatus(Invoice invoice){
        return this.invoiceRepository.findByStatus(invoice.getClient_id());
    }

    @Transactional
    public void update(Long invoice_id, Long client_id, Double debt, LocalDate date, boolean status) {
        Invoice invoiceToUpdate = this.invoiceRepository
                                        .findById(invoice_id)
                                        .orElseThrow(() -> new EntityNotFoundException("Invoice Not Found"));
        
        if (Objects.nonNull(client_id))
            invoiceToUpdate.setClient_id(client_id);

        if (Objects.nonNull(debt) && debt > 0)
            invoiceToUpdate.setDebt(debt);
        
        if (Objects.nonNull(date))
            invoiceToUpdate.setDate(date);

        if (Objects.nonNull(status))
            invoiceToUpdate.setStatus(status);
    }
}
