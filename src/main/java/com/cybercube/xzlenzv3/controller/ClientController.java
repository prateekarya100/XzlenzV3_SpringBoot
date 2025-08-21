package com.cybercube.xzlenzv3.controller;

import com.cybercube.xzlenzv3.model.Client.Client;
import com.cybercube.xzlenzv3.model.Client.dto.ClientResponse;
import com.cybercube.xzlenzv3.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<ClientResponse> createClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.createClient(client));
    }



    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','CLIENT_ADMIN')")
    public ResponseEntity<ClientResponse> getClient(@PathVariable Integer id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','CLIENT_ADMIN')")
    public ResponseEntity<ClientResponse> updateClient(@PathVariable Integer id, @RequestBody Client client) {
        return ResponseEntity.ok(clientService.updateClient(id, client));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/{id}/upload-logo")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','CLIENT_ADMIN')")
    public ResponseEntity<String> uploadLogo(@PathVariable Integer id, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(clientService.uploadLogo(id, file));
    }
}
