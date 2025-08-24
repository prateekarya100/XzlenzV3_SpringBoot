package com.cybercube.xzlenzv3.service;

import com.cybercube.xzlenzv3.constants.UserProfileStatus;
import com.cybercube.xzlenzv3.dto.superAdminDTO.SuperAdminRequestDTO;
import com.cybercube.xzlenzv3.exceptions.SuperAdminAlreadyExists;
import com.cybercube.xzlenzv3.model.User.User;
import com.cybercube.xzlenzv3.model.User.UserProfile;
import com.cybercube.xzlenzv3.model.User.UserProfileType;
import com.cybercube.xzlenzv3.repository.UserProfileRepository;
import com.cybercube.xzlenzv3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SuperAdminService {

    private final UserRepository userRepo;
    private final UserProfileRepository profileRepo;
    private final PasswordEncoder passwordEncoder;

    public User createSuperAdmin(SuperAdminRequestDTO dto) {

        // check if a super admin already exists
        boolean existsSuper = userRepo.existsBySsoId(UserProfileType.SUPER.getUserProfileType());
        if (existsSuper) {
            throw new SuperAdminAlreadyExists("Super admin already exists, cannot create another one.");
        }


        // create super admin profile if not exists
        UserProfile superProfile = profileRepo.findByType(UserProfileType.SUPER.getUserProfileType())
                .orElseGet(() -> profileRepo.save(
                        UserProfile.builder().type(UserProfileType.SUPER.getUserProfileType()).build()
                ));

        User superUser = User.builder()
                .ssoId(dto.getSsoId())
                .password(passwordEncoder.encode(dto.getPassword()))
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .contactNumber(dto.getContactNumber())
                .imageUrl(dto.getImageUrl())
                .designation(UserProfileType.SUPER.getUserProfileType())
                .profileStatus(UserProfileStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        superUser.getUserProfiles().add(superProfile);
        return userRepo.save(superUser);
    }

}
