package com.cybercube.xzlenzv3.service.client_service;

import com.cybercube.xzlenzv3.constants.UserProfileStatus;
import com.cybercube.xzlenzv3.dto.ClientDto;
import com.cybercube.xzlenzv3.exceptions.ClientCompanyAlreadyExists;
import com.cybercube.xzlenzv3.model.Client.Client;
import com.cybercube.xzlenzv3.model.Client.dto.ClientAdminDTO;
import com.cybercube.xzlenzv3.model.Client.dto.ClientMapper;
import com.cybercube.xzlenzv3.model.Client.dto.ClientWithAdminRequestDTO;
import com.cybercube.xzlenzv3.model.User.User;
import com.cybercube.xzlenzv3.model.User.UserProfile;
import com.cybercube.xzlenzv3.model.User.UserProfileType;
import com.cybercube.xzlenzv3.repository.ClientRepository;
import com.cybercube.xzlenzv3.repository.UserProfileRepository;
import com.cybercube.xzlenzv3.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final UserProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Client createClientWithAdmin(ClientWithAdminRequestDTO request, User superAdmin) {
        // Save Client
        Client client = new Client();
        client.setCompanyName(request.getCompanyName());
        client.setContactNumber(request.getContactNumber());
        client.setEmail(request.getEmail());
        client.setAddress(request.getAddress());
        client.setCity(request.getCity());
        client.setState(request.getState());
        client.setCountry(request.getCountry());
        client.setZipCode(request.getZipCode());
        client.setCompanyUrl(request.getCompanyUrl());
        client.setImageUrl(request.getImageUrl());
        client.setCreatedBy(superAdmin);
        client.setCreatedAt(LocalDateTime.now());
        client.setClientUser(new ArrayList<>());

        // Save Client Admin
        ClientAdminDTO adminDto = request.getClientAdmin();

        User admin = User.builder()
                .ssoId(adminDto.getEmail())
                .firstName(adminDto.getFirstName())
                .lastName(adminDto.getLastName())
                .email(adminDto.getEmail())
                .password(passwordEncoder.encode(adminDto.getPassword()))
                .contactNumber(adminDto.getContactNumber())
                .imageUrl(adminDto.getImageUrl())
                .designation("CLIENT_ADMIN")
                .profileStatus("active")
                .client(client)
                .createdBy(superAdmin)
                .createdAt(LocalDateTime.now())
                .build();

        // Assign CLIENT_ADMIN profile
        UserProfile clientAdminProfile = profileRepository.findByType(UserProfileType.CLIENT_ADMIN.getUserProfileType())
                .orElseGet(() -> profileRepository.save(
                        UserProfile.builder()
                                .type(UserProfileType.CLIENT_ADMIN.getUserProfileType())
                                .build()
                ));
        admin.getUserProfiles().add(clientAdminProfile);

        // Save admin
        userRepository.save(admin);

        // relation maintain
        client.getClientUser().add(admin);

        return clientRepository.save(client);
    }


    public ClientDto getClientById(Long clientId) {
        Client client = clientRepository.findById(clientId.intValue())
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));
        return ClientMapper.toDto(client);
    }

    @Transactional
    public ClientDto updateClient(Long clientId, ClientDto clientDto, User updatedByUser) {
        Client client = clientRepository.findById(clientId.intValue())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        if (clientDto.getCompanyName() != null) client.setCompanyName(clientDto.getCompanyName());
        if (clientDto.getContactNumber() != null) client.setContactNumber(clientDto.getContactNumber());
        if (clientDto.getEmail() != null) client.setEmail(clientDto.getEmail());
        if (clientDto.getAddress() != null) client.setAddress(clientDto.getAddress());
        if (clientDto.getCity() != null) client.setCity(clientDto.getCity());
        if (clientDto.getState() != null) client.setState(clientDto.getState());
        if (clientDto.getCountry() != null) client.setCountry(clientDto.getCountry());
        if (clientDto.getZipCode() != null) client.setZipCode(clientDto.getZipCode());
        if (clientDto.getCompanyUrl() != null) client.setCompanyUrl(clientDto.getCompanyUrl());
        if (clientDto.getImageUrl() != null) client.setImageUrl(clientDto.getImageUrl());

        client.setUpdatedAt(LocalDateTime.now());
        client.setUpdatedBy(updatedByUser); // ✅ track who updated

        try {
            return ClientMapper.toDto(clientRepository.save(client));
        } catch (Exception e) {
            throw new RuntimeException("Failed to update client: " + e.getMessage(), e);
        }
    }


    @Transactional
    public void deleteClient(Long clientId) {
        Client client = clientRepository.findById(clientId.intValue())
                .orElseThrow(() -> new RuntimeException("Client not found with id " + clientId));

        // delete associated users
        userRepository.deleteAllByClientId(clientId);

        // delete client
        clientRepository.delete(client);
    }
}
