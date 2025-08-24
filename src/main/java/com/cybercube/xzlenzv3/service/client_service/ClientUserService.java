package com.cybercube.xzlenzv3.service.client_service;

import com.cybercube.xzlenzv3.constants.UserProfileStatus;
import com.cybercube.xzlenzv3.model.Client.Client;
import com.cybercube.xzlenzv3.model.User.User;
import com.cybercube.xzlenzv3.model.User.UserProfile;
import com.cybercube.xzlenzv3.model.User.UserProfileType;
import com.cybercube.xzlenzv3.model.User.dto.ClientWithUsersDTO;
import com.cybercube.xzlenzv3.model.User.dto.UserDTO;
import com.cybercube.xzlenzv3.repository.ClientRepository;
import com.cybercube.xzlenzv3.repository.UserProfileRepository;
import com.cybercube.xzlenzv3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientUserService {

    private final UserRepository userRepository;
    private final UserProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;

    private final String PROFILE_PICTURE_UPLOAD_DIR = "src/main/resources/profiles";
    private final Path root = Paths.get("src/main/resources/client");



    @Transactional
    public User createClientUser(User userRequest, User clientAdmin) {

        // Debug info
        System.out.println("Logged-in ClientAdmin email: " + clientAdmin.getEmail());
        System.out.println("Roles: " + clientAdmin.getUserProfiles().stream().map(UserProfile::getType).toList());

        if (clientAdmin == null || !clientAdmin.hasRole("CLIENT_ADMIN")) {
            throw new RuntimeException("Client Admin not found");
        }

        // SSO ID = email
        userRequest.setSsoId(userRequest.getEmail());

        // Assign same client
        userRequest.setClient(clientAdmin.getClient());

        // ✅ Check for duplicate user in same client
        boolean exists = userRepository.existsBySsoIdAndClient(userRequest.getEmail(), clientAdmin.getClient());
        if (exists) {
            throw new RuntimeException("User with email " + userRequest.getEmail() + " already exists in your company");
        }

        // Encode password
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        // Set designation, status, timestamps
        userRequest.setDesignation("CLIENT_USER");
        userRequest.setProfileStatus(UserProfileStatus.ACTIVE);
        userRequest.setCreatedAt(LocalDateTime.now());
        userRequest.setUpdatedAt(LocalDateTime.now());
        userRequest.setCreatedBy(clientAdmin);
        userRequest.setUpdatedBy(clientAdmin);

        // Assign CLIENT_USER profile
        UserProfile clientUserProfile = profileRepository.findByType(UserProfileType.CLIENT_USER.getUserProfileType())
                .orElseGet(() -> profileRepository.save(
                        UserProfile.builder()
                                .type(UserProfileType.CLIENT_USER.getUserProfileType())
                                .build()
                ));

        Set<UserProfile> profiles = new HashSet<>();
        profiles.add(clientUserProfile);
        userRequest.setUserProfiles(profiles);

        // Save new client user
        return userRepository.save(userRequest);
    }

    @Transactional
    public User updateClientUser(Long userId, User userRequest, User clientAdmin) {
        User existingUser = (User) userRepository.findByIdAndClient(userId, clientAdmin.getClient())
                .orElseThrow(() -> new RuntimeException("User not found or not authorized"));

        existingUser.setSsoId(userRequest.getEmail());
        existingUser.setFirstName(userRequest.getFirstName());
        existingUser.setLastName(userRequest.getLastName());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setContactNumber(userRequest.getContactNumber());
        existingUser.setImageUrl(userRequest.getImageUrl());
        existingUser.setUpdatedAt(LocalDateTime.now());
        existingUser.setUpdatedBy(clientAdmin);

        return userRepository.save(existingUser);
    }

    @Transactional
    public void deleteClientUser(Long userId, User clientAdmin) {
        User existingUser = (User) userRepository.findByIdAndClient(userId, clientAdmin.getClient())
                .orElseThrow(() -> new RuntimeException("User not found or not authorized"));

        userRepository.delete(existingUser);
    }


    @Transactional
    public User fetchClientUserById(Long clientUserId, User clientAdmin) {
        return userRepository.findByIdAndClient(clientUserId, clientAdmin.getClient())
                .orElseThrow(() -> new RuntimeException("User not found or not authorized"));
    }


    @Transactional
    public List<User> findByClient(Client client) {
        return userRepository.findByClient(client);
    }


    @Transactional
    public ClientWithUsersDTO fetchClientWithUsers(User clientAdmin) {
        Client client = clientAdmin.getClient();
        if (client == null) {
            throw new RuntimeException("ClientAdmin is not associated with any client");
        }

        // Fetch users of this client
        List<User> users = userRepository.findByClient(client);

        // Map users to DTO without client info inside each user
        List<UserDTO> userDTOs = users.stream()
                .map(u -> new UserDTO(
                        u.getId(),
                        u.getFirstName(),
                        u.getLastName(),
                        u.getEmail(),
                        u.getContactNumber(),
                        u.getDesignation(),
                        u.getProfileStatus()
                ))
                .collect(Collectors.toList());

        return new ClientWithUsersDTO(
                client.getId(),
                client.getCompanyName(),
                userDTOs
        );
    }


    @Transactional
    public String uploadUserProfilePicture(Long userId, MultipartFile file) {
        User user = userRepository.findById(userId.intValue())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String fileName = "user_" + userId + "_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();

        Path uploadPath = Paths.get(PROFILE_PICTURE_UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                throw new RuntimeException("Could not create upload directory!", e);
            }
        }

        Path path = uploadPath.resolve(fileName);

        try {
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }

        String relativePath = "/uploads/" + fileName;
        user.setImageUrl(relativePath);
        userRepository.save(user);

        return relativePath;
    }


    // Upload client logo
    @Transactional
    public String uploadClientLogo(Long clientId, MultipartFile file) {
        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
            Client client = clientRepository.findById(clientId.intValue())
                    .orElseThrow(() -> new RuntimeException("Client not found"));

            String email = client.getEmail(); // example: cognizant@company.com
            String companyPrefix = email.split("@")[0]; // "cognizant"
            companyPrefix = companyPrefix.replaceAll("[^a-zA-Z0-9]", "");

            String fileName = companyPrefix + "_" + clientId + "_" + file.getOriginalFilename();

            Path destination = root.resolve(fileName);
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

            client.setImageUrl("client/" + fileName);
            clientRepository.save(client);

            return "client/" + fileName;
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }



    @Transactional
    public User changeClientUserStatus(Long userId, String active, User admin){
        User user = userRepository.findById(userId.intValue())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(active.equals(UserProfileStatus.ACTIVE)){
            user.setProfileStatus(UserProfileStatus.ACTIVE);
        }
        else if(active.equals(UserProfileStatus.INACTIVE)){
            user.setProfileStatus(UserProfileStatus.INACTIVE);
        }
        else if(active.equals(UserProfileStatus.DELETED)){
            user.setProfileStatus(UserProfileStatus.DELETED);
        } else {
            throw new RuntimeException("Invalid status: " + active);
        }

        user.setUpdatedAt(LocalDateTime.now());
        user.setUpdatedBy(admin);

        return userRepository.save(user);
    }
}
