package com.final_project.final_project.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.final_project.final_project.entity.Invoice;
import com.final_project.final_project.service.InvoiceService;

@RestController
@RequestMapping("/final/invoice")
@RequestScope
public class InvoiceController {
    
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/get")
    public List<Invoice> getAll() {
        return this.invoiceService.getInvoices();
    }

    @PostMapping("/save")
    public ResponseEntity<?> add(@RequestBody Invoice invoice) {
        this.invoiceService.create(invoice);
        return ResponseEntity.ok("Fatura Eklendi");
    }

    @DeleteMapping("/delete/{invoice_id}")
    public ResponseEntity<?> delete(@PathVariable("invoice_id") Long invoice_id) {
        this.invoiceService.delete(invoice_id);
        return ResponseEntity.ok("Fatura Silindi");
    }

    @GetMapping("/get/{invoice_id}")
    public ResponseEntity<?> getById(@PathVariable("invoice_id") Long invoice_id) {
        try {
            return ResponseEntity.ok(this.invoiceService.findById(invoice_id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{invoice_id}")
    public ResponseEntity<?> update(@RequestParam(required = false) Long client_id, @RequestParam(required = false) Double debt, 
                                    @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate date, 
                                    @RequestParam(required = false) boolean status,
                                    @PathVariable("invoice_id") Long invoice_id) {
        try {
            this.invoiceService.update(invoice_id, client_id, debt, date, status);
            return ResponseEntity.ok("Invoice is Updated");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/status")
    public ResponseEntity<?> getInvoiceStatus(@RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoice.getClient_id() + " id numarali musterinin " 
                                + this.invoiceService.getInvoiceStatus(invoice).toString() 
                                + " adet odenmemis faturasi bulunuyor");
    }
}
