package com.cybercube.xzlenzv3.service;

import com.cybercube.xzlenzv3.exceptions.ResourceNotFoundException;
import com.cybercube.xzlenzv3.model.Client.Client;
import com.cybercube.xzlenzv3.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepo;

    public Client createClient(Client client) {
        client.setCreatedAt(LocalDateTime.now());
        return clientRepo.save(client);
    }

    public Client updateClient(Integer id, Client updatedClient) {
        Client existing = clientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + id));

        existing.setUpdatedAt(LocalDateTime.now());

        if (updatedClient.getCompanyName() != null) existing.setCompanyName(updatedClient.getCompanyName());
        if (updatedClient.getContactNumber() != null) existing.setContactNumber(updatedClient.getContactNumber());
        if (updatedClient.getEmail() != null) existing.setEmail(updatedClient.getEmail());
        if (updatedClient.getAddress() != null) existing.setAddress(updatedClient.getAddress());
        if (updatedClient.getCity() != null) existing.setCity(updatedClient.getCity());
        if (updatedClient.getState() != null) existing.setState(updatedClient.getState());
        if (updatedClient.getCountry() != null) existing.setCountry(updatedClient.getCountry());
        if (updatedClient.getZipCode() != null) existing.setZipCode(updatedClient.getZipCode());
        if (updatedClient.getCompanyUrl() != null) existing.setCompanyUrl(updatedClient.getCompanyUrl());
        if (updatedClient.getImageUrl() != null) existing.setImageUrl(updatedClient.getImageUrl());

        return clientRepo.save(existing);
    }

    public List<Client> getAllClients() {
        return clientRepo.findAll();
    }

    public void deleteClient(Integer id) {
        if (!clientRepo.existsById(id)) {
            throw new ResourceNotFoundException("Client not found with ID: " + id);
        }
        clientRepo.deleteById(id);
    }
}
