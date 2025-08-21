package com.cybercube.xzlenzv3.service;

import com.cybercube.xzlenzv3.exceptions.ResourceNotFoundException;
import com.cybercube.xzlenzv3.exceptions.UnauthorizedAccessException;
import com.cybercube.xzlenzv3.model.Client.Client;
import com.cybercube.xzlenzv3.model.Client.dto.ClientAdminResponse;
import com.cybercube.xzlenzv3.model.Client.dto.ClientResponse;
import com.cybercube.xzlenzv3.model.User.User;
import com.cybercube.xzlenzv3.model.User.UserProfile;
import com.cybercube.xzlenzv3.repository.ClientRepository;
import com.cybercube.xzlenzv3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ClientResponse createClient(Client client) {
        client.setCreatedAt(LocalDateTime.now());

        Client savedClient = clientRepo.save(client);

        // create default CLIENT_ADMIN user for this client
        User clientAdmin = User.builder()
                .ssoId("admin_" + savedClient.getId())
                .password(passwordEncoder.encode("Admin@123"))
                .firstName("Client")
                .lastName("Admin")
                .email("admin" + savedClient.getId() + "@client.com")
                .contactNumber("+911234567890")
                .profileStatus("active")
                .client(savedClient)
                .createdAt(LocalDateTime.now())
                .designation("CLIENT_ADMIN")
                .userProfiles(new HashSet<>())
                .build();

        // Fetch CLIENT_ADMIN profile
        UserProfile adminProfile = userService.getProfileByType("CLIENT_ADMIN");
        clientAdmin.getUserProfiles().add(adminProfile);

        // Save the user
        userService.saveUser(clientAdmin);

        // Return DTO instead of entity
        return mapToResponse(savedClient);
    }

    public ClientResponse getClientById(Integer id) {
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + id));

        User currentUser = getCurrentUser();

        if (currentUser.hasRole("CLIENT_ADMIN") &&
                !currentUser.getClient().getId().equals(client.getId())) {
            throw new UnauthorizedAccessException("You are not allowed to access other client's details");
        }

        return mapToResponse(client);
    }

    public String uploadLogo(Integer clientId, MultipartFile file) {
        Client client = clientRepo.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + clientId));

        User currentUser = getCurrentUser();

        if (currentUser.hasRole("CLIENT_ADMIN") &&
                !currentUser.getClient().getId().equals(client.getId())) {
            throw new UnauthorizedAccessException("You are not allowed to upload logo for another company");
        }

        String filePath = "uploads/clients/" + clientId + "_logo.png";
        try {
            File dest = new File(filePath);
            dest.getParentFile().mkdirs();
            file.transferTo(dest);
            client.setImageUrl(filePath);
            clientRepo.save(client);
        } catch (IOException e) {
            throw new RuntimeException("Error saving logo", e);
        }
        return filePath;
    }

    public ClientResponse updateClient(Integer id, Client updatedClient) {
        Client existingClient = clientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + id));

        User currentUser = getCurrentUser();

        if (currentUser.hasRole("CLIENT_ADMIN") &&
                !currentUser.getClient().getId().equals(existingClient.getId())) {
            throw new UnauthorizedAccessException("You are not allowed to update other client's details");
        }

        existingClient.setCompanyName(updatedClient.getCompanyName());
        existingClient.setContactNumber(updatedClient.getContactNumber());
        existingClient.setEmail(updatedClient.getEmail());
        existingClient.setAddress(updatedClient.getAddress());
        existingClient.setCity(updatedClient.getCity());
        existingClient.setState(updatedClient.getState());
        existingClient.setCountry(updatedClient.getCountry());
        existingClient.setZipCode(updatedClient.getZipCode());
        existingClient.setCompanyUrl(updatedClient.getCompanyUrl());
        existingClient.setUpdatedAt(LocalDateTime.now());

        Client saved = clientRepo.save(existingClient);
        return mapToResponse(saved);
    }

    public void deleteClient(Integer id) {
        User currentUser = getCurrentUser();

        if (currentUser.hasRole("CLIENT_ADMIN")) {
            throw new UnauthorizedAccessException("CLIENT_ADMIN cannot delete clients");
        }

        Client existingClient = clientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + id));

        clientRepo.delete(existingClient);
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userService.findByUsername(username);
    }

    private ClientResponse mapToResponse(Client client) {
        User admin = userRepository.findFirstByClientAndDesignation(client, "CLIENT_ADMIN")
                .orElse(null);

        ClientAdminResponse adminDto = null;
        if (admin != null) {
            adminDto = new ClientAdminResponse(
                    admin.getId(),
                    admin.getSsoId(),
                    admin.getUserProfiles().stream()
                            .map(UserProfile::getType)
                            .collect(Collectors.toList())
            );
        }

        return new ClientResponse(
                client.getId(),
                client.getCompanyName(),
                client.getEmail(),
                adminDto
        );
    }
}
