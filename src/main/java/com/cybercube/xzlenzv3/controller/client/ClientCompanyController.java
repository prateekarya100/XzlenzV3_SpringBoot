package com.cybercube.xzlenzv3.controller.client;

import com.cybercube.xzlenzv3.config.CustomUserDetails;
import com.cybercube.xzlenzv3.dto.ClientDto;
import com.cybercube.xzlenzv3.service.client_service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client/company")
@RequiredArgsConstructor
public class ClientCompanyController {

    private final ClientService clientService;

    // fetch client company info by ID
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping(value = "/{clientId}")
    public ResponseEntity<ClientDto> getClientCompany(@PathVariable Long clientId) {
        return ResponseEntity.ok(clientService.getClientById(clientId));
    }

    // update client company info by client company ID
    @PutMapping("/update-info-by-id/{clientId}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<ClientDto> updateClientCompany(
            @PathVariable Long clientId,
            @RequestBody ClientDto clientDto,
            @AuthenticationPrincipal CustomUserDetails loggedInUser
    ) {
        return ResponseEntity.ok(clientService.updateClient(clientId, clientDto, loggedInUser.getUser()));
    }


    // delete client company by ID
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/delete-info-by-id/{clientId}")
    public ResponseEntity<String> deleteClientCompany(@PathVariable Long clientId) {
        clientService.deleteClient(clientId);
        return ResponseEntity.ok("Client company and associated users deleted successfully!");
    }

}
