package com.cybercube.xzlenzv3.model.Client.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientAdminDTO {

    @NotBlank(message = "SSO ID cannot be empty")
    private String ssoId;

    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotBlank(message = "Contact number cannot be empty")
    private String contactNumber;

    private String imageUrl;
}
