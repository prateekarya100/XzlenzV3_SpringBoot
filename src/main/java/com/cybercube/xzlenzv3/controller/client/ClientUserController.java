package com.cybercube.xzlenzv3.controller.client;

import com.cybercube.xzlenzv3.config.CustomUserDetails;
import com.cybercube.xzlenzv3.constants.UserProfileStatus;
import com.cybercube.xzlenzv3.model.User.User;
import com.cybercube.xzlenzv3.model.User.dto.ClientWithUsersDTO;
import com.cybercube.xzlenzv3.model.User.dto.UserDTO;
import com.cybercube.xzlenzv3.service.client_service.ClientUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/client/users")
@RequiredArgsConstructor
public class ClientUserController {

    @Autowired
    private ClientUserService clientUserService;

    // ClientAdmin can create ClientUser in their own company
    @PostMapping
    @PreAuthorize("hasRole('CLIENT_ADMIN')")
    public ResponseEntity<?> createClientUser(
            @RequestBody User userRequest,
            @AuthenticationPrincipal CustomUserDetails loggedInUser
    ) {
        if (loggedInUser == null) {
            throw new RuntimeException("Logged-in user details missing!");
        }

        User clientAdmin = loggedInUser.getUser();
        if (clientAdmin == null) {
            throw new RuntimeException("Logged-in ClientAdmin is null!");
        }

        System.out.println("Logged-in ClientAdmin email: " + clientAdmin.getEmail());
        System.out.println("Roles: " + clientAdmin.getUserProfiles().stream().map(p -> p.getType()).toList());

        User savedUser = clientUserService.createClientUser(userRequest, clientAdmin);

        return ResponseEntity.ok(
                Map.of(
                        "message", "Client User created successfully",
                        "clientUserEmail", savedUser.getEmail()
                )
        );
    }



    @PreAuthorize("hasRole('CLIENT_ADMIN')")
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateClientUser(
            @PathVariable Long userId,
            @RequestBody User userRequest,
            @AuthenticationPrincipal CustomUserDetails loggedInUser
    ) {
        User clientAdmin = loggedInUser.getUser();
        User updatedUser = clientUserService.updateClientUser(userId, userRequest, clientAdmin);

        return ResponseEntity.ok(Map.of(
                "message", "Client User updated successfully",
                "clientUserEmail", updatedUser.getEmail()
        ));
    }



    @PreAuthorize("hasRole('CLIENT_ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteClientUser(
            @PathVariable Long userId,
            @AuthenticationPrincipal CustomUserDetails loggedInUser
    ) {
        User clientAdmin = loggedInUser.getUser();
        clientUserService.deleteClientUser(userId, clientAdmin);

        return ResponseEntity.ok(Map.of(
                "message", "Client User deleted successfully"
        ));
    }



    @GetMapping("/get-all-users")
    @PreAuthorize("hasRole('CLIENT_ADMIN')")
    public ResponseEntity<ClientWithUsersDTO> getAllClientUsers(
            @AuthenticationPrincipal CustomUserDetails loggedInUser) {

        if (loggedInUser == null || loggedInUser.getUser() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        User clientAdmin = loggedInUser.getUser();
        ClientWithUsersDTO users = clientUserService.fetchClientWithUsers(clientAdmin);

        return ResponseEntity.ok(users);
    }


    @PreAuthorize("hasRole('CLIENT_ADMIN', 'CLIENT_USER')")
    @GetMapping("/get-client-user-by-id/{clientUserId}")
    public ResponseEntity<User> getClientUserById(
            @PathVariable Long clientUserId,
            @AuthenticationPrincipal CustomUserDetails loggedInUser
    ) {
        User clientAdmin = loggedInUser.getUser();
        User user = clientUserService.fetchClientUserById(clientUserId, clientAdmin);
        return ResponseEntity.ok(user);
    }


    @PreAuthorize("hasRole('CLIENT_ADMIN')")
    @PatchMapping(value = "/change-status/{userId}")
    public ResponseEntity<?> changeClientUserStatus(
            @PathVariable Long userId,
            @RequestParam("status") String status,
            @AuthenticationPrincipal CustomUserDetails loggedInUser
    ) {
        User admin = loggedInUser.getUser();
        User user = clientUserService.changeClientUserStatus(userId, status, admin);

        return ResponseEntity.ok(Map.of(
                "message", "Client User status changed to " + status,
                "clientUserEmail", user.getEmail()
        ));
    }

}
