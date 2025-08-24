package com.cybercube.xzlenzv3.controller;


import com.cybercube.xzlenzv3.dto.superAdminDTO.SuperAdminRequestDTO;
import com.cybercube.xzlenzv3.dto.superAdminDTO.SuperAdminResponseDTO;
import com.cybercube.xzlenzv3.model.Client.Client;
import com.cybercube.xzlenzv3.model.Client.dto.ClientAdminDTO;
import com.cybercube.xzlenzv3.model.Client.dto.ClientWithAdminRequestDTO;
import com.cybercube.xzlenzv3.model.User.User;
import com.cybercube.xzlenzv3.service.SuperAdminService;
import com.cybercube.xzlenzv3.service.client_service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/super-admin")
@RequiredArgsConstructor
public class SuperAdminController {

    @Autowired
    private SuperAdminService superAdminService;

    @Autowired
    private ClientService clientService;

    // create super admin - only once
    @PostMapping(value = "/create")
    public ResponseEntity<SuperAdminResponseDTO> createSuperAdmin(@Valid @RequestBody SuperAdminRequestDTO dto) {
         Optional<User> superAdmin = Optional.ofNullable(superAdminService.createSuperAdmin(dto));
         if (superAdmin.isEmpty()){
                return ResponseEntity.badRequest().build();
            } else {
                SuperAdminResponseDTO responseDTO = new SuperAdminResponseDTO();
                responseDTO.setSsoId(superAdmin.get().getSsoId());
                responseDTO.setMessage("Super admin created successfully");
                return ResponseEntity.ok(responseDTO);
         }
    }

    // create client along with client admin - only by super admin
    @PostMapping("/create-client")
    public ResponseEntity<?> createClient(
            @Valid @RequestBody ClientWithAdminRequestDTO request,
            BindingResult bindingResult,
            @AuthenticationPrincipal User superAdmin) {

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(Map.of("errors", errors));
        }

        Client createdClient = clientService.createClientWithAdmin(request, superAdmin);

        return ResponseEntity.ok(Map.of(
                "message", "Client and Client Admin created successfully",
                "clientName", createdClient.getCompanyName(),
                "adminEmail", request.getClientAdmin().getEmail()
        ));
    }


}
