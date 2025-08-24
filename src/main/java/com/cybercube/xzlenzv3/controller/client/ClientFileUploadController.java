package com.cybercube.xzlenzv3.controller.client;

import com.cybercube.xzlenzv3.config.CustomUserDetails;
import com.cybercube.xzlenzv3.model.User.User;
import com.cybercube.xzlenzv3.service.client_service.ClientUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientFileUploadController {

    private final ClientUserService clientUserService;

    // Upload self profile picture
    @PostMapping("/upload-self-profile-picture")
    @PreAuthorize("hasRole('CLIENT_ADMIN')")
    public ResponseEntity<Map<String, String>> uploadSelfProfilePicture(
            @RequestParam("file") MultipartFile file,
            @AuthenticationPrincipal CustomUserDetails loggedInUser
    ) {
        User admin = loggedInUser.getUser();
        String path = clientUserService.uploadUserProfilePicture(Long.valueOf(admin.getId()), file);
        return ResponseEntity.ok(Map.of("message", "Profile picture uploaded successfully", "filePath", path));
    }

    // Upload any client user's profile picture
    @PostMapping("/upload-user-profile-picture/{userId}")
    @PreAuthorize("hasRole('CLIENT_ADMIN')")
    public ResponseEntity<Map<String, String>> uploadUserProfilePicture(
            @PathVariable Long userId,
            @RequestParam("file") MultipartFile file
    ) {
        String path = clientUserService.uploadUserProfilePicture((long) Math.toIntExact(userId), file);
        return ResponseEntity.ok(Map.of("message", "User profile picture uploaded successfully", "filePath", path));
    }

    // Upload client logo
    @PostMapping("/upload-client-logo/{clientId}")
    @PreAuthorize("hasRole('CLIENT_ADMIN')")
    public ResponseEntity<Map<String, String>> uploadClientLogo(
            @PathVariable Long clientId,
            @RequestParam("file") MultipartFile file,
            @AuthenticationPrincipal CustomUserDetails loggedInUser
    ) {
        User admin = loggedInUser.getUser();

        // Ensure admin belongs to the same client
        if (admin.getClient() == null || !admin.getClient().getId().equals(clientId.intValue())) {
            return ResponseEntity.status(403).body(Map.of("error", "You are not allowed to upload logo for another client"));
        }

        String path = clientUserService.uploadClientLogo(clientId, file);
        return ResponseEntity.ok(Map.of("message", "Client logo uploaded successfully", "filePath", path));
    }

}
