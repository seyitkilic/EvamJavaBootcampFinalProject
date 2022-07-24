package com.final_project.final_project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.final_project.final_project.entity.Invoice;


@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    
    @Query(value = "SELECT COUNT(status) FROM Invoice i WHERE status = false AND i.client_id = :client_id")
    Long findByStatus(@Param("client_id") Long client_id);
    
}
