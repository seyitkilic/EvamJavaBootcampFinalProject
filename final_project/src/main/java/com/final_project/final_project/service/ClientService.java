package com.final_project.final_project.service;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.final_project.final_project.entity.Client;
import com.final_project.final_project.repository.ClientRepository;

@Service
public class ClientService {
    
    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public List<Client> getClient() {
        return clientRepository.findAll();
    }

    public void create(Client toAdd){
        clientRepository.save(toAdd);
    }

    public void delete(Long client_id) {
        clientRepository.deleteById(client_id);
    }

    public Client findById(Long client_id) {
        return this.clientRepository
                    .findById(client_id)
                    .orElseThrow(() -> new EntityNotFoundException("Client Not Found"));
    }

    @Transactional
    public void update (String first_name, String last_name, Long client_id) {
        Client clientToUpdate = this.clientRepository
                                    .findById(client_id)
                                    .orElseThrow(() -> new EntityNotFoundException("Client Not Found"));

        if (Objects.nonNull(first_name) && first_name.length() > 0 && !clientToUpdate.getFirst_name().equals(first_name))
           clientToUpdate.setFirst_name(first_name);
        if (Objects.nonNull(last_name) && last_name.length() > 0 && !clientToUpdate.getLast_name().equals(last_name))
            clientToUpdate.setLast_name(last_name);
    }
}
