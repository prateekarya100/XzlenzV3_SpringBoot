package com.cybercube.xzlenzv3.dto.superAdminDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SuperAdminRequestDTO {

    @NotEmpty(message = "SSO ID cannot be empty")
    private String ssoId;

    @NotEmpty(message = "Password cannot be empty")
    @Size(max = 100, message = "Password cannot be too long")
    private String password;

    @NotEmpty(message = "First Name cannot be empty")
    @Size(max = 15, message = "First Name cannot be longer than 15 characters")
    @Pattern(regexp = "^[A-Za-z0-9 ]*$", message = "First name must contain only letters, numbers, spaces")
    private String firstName;

    @NotEmpty(message = "Last Name cannot be empty")
    @Size(max = 15, message = "Last Name cannot be longer than 15 characters")
    @Pattern(regexp = "^[A-Za-z0-9 ]*$", message = "Last name must contain only letters, numbers, spaces")
    private String lastName;

    @NotEmpty(message = "Email cannot be empty")
    @Size(max = 50, message = "Email cannot be longer than 50 characters")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Contact number cannot be empty")
    @Pattern(
            regexp = "^\\+?[0-9]{1,4}?[0-9]{8,15}$",
            message = "Invalid phone number. It should be between 8 and 15 digits, and may include a country code like +91."
    )
    private String contactNumber;

    @Size(max = 50, message = "Image URL cannot be longer than 50 characters")
    private String imageUrl;
}
