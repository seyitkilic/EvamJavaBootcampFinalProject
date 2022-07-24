package com.final_project.final_project.controller;

import java.util.List;


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

import com.final_project.final_project.entity.Client;
import com.final_project.final_project.service.ClientService;

@RestController
@RequestMapping("/final/client")
@RequestScope
public class ClientController {
    
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/get")
    public List<Client> getAll() {
        return this.clientService.getClient();
    }

    @PostMapping("/save")
    public ResponseEntity<?> add(@RequestBody Client client) {
        this.clientService.create(client);
        return ResponseEntity.ok("Musteri Eklendi");
    }

    @DeleteMapping("/delete/{client_id}")
    public ResponseEntity<?> delete(@PathVariable("client_id") Long client_id) {
        this.clientService.delete(client_id);
        return ResponseEntity.ok("Musteri Silindi");
    }

    @GetMapping("/get/{client_id}")
    public ResponseEntity<?> getById(@PathVariable("client_id") Long client_id){
        try{
            return ResponseEntity.ok(this.clientService.findById(client_id));
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } 
    }

    @PutMapping("/update/{client_id}")
    public ResponseEntity<?> update(@RequestParam(required = false) String first_name, @RequestParam(required = false) String last_name, 
                                    @PathVariable("client_id") Long client_id){
        try {
            this.clientService.update(first_name, last_name, client_id);
            return ResponseEntity.ok("Client is Updated");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
