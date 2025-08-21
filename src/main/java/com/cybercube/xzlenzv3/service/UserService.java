package com.cybercube.xzlenzv3.service;

import com.cybercube.xzlenzv3.exceptions.ResourceNotFoundException;
import com.cybercube.xzlenzv3.model.Client.dto.CreateUserRequest;
import com.cybercube.xzlenzv3.model.User.User;
import com.cybercube.xzlenzv3.model.User.UserProfile;
import com.cybercube.xzlenzv3.repository.ClientRepository;
import com.cybercube.xzlenzv3.repository.UserProfileRepository;
import com.cybercube.xzlenzv3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserProfileRepository profileRepo;

    @Autowired
    private ClientRepository clientRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(CreateUserRequest req) {
        User user = new User();

        user.setSsoId(req.getSsoId());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setEmail(req.getEmail());
        user.setContactNumber(req.getContactNumber());
        user.setCreatedAt(LocalDateTime.now());
        user.setProfileStatus("active"); // default status

        // Optional: set createdBy if available
        // user.setCreatedBy(currentLoggedInUser);

        user.setClient(clientRepo.findById(req.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + req.getClientId())));

        Set<UserProfile> profiles = req.getRoles().stream()
                .map(role -> profileRepo.findByType(role)
                        .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + role)))
                .collect(Collectors.toSet());

        user.setUserProfiles(profiles);

        return userRepo.save(user);
    }

    public void changeStatus(Integer id, String status) {
        if (!status.matches("active|inactive|deleted")) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }

        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        user.setProfileStatus(status);
        user.setUpdatedAt(LocalDateTime.now());

        // Optional: set updatedBy if available
        // user.setUpdatedBy(currentLoggedInUser);

        userRepo.save(user);
    }
}
