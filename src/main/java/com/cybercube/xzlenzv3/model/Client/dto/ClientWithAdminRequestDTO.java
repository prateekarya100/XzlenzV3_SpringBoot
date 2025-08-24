package com.cybercube.xzlenzv3.model.Client.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientWithAdminRequestDTO {

    @NotBlank(message = "Company name cannot be empty")
    private String companyName;

    @NotBlank(message = "Contact number cannot be empty")
    private String contactNumber;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String companyUrl;
    private String imageUrl;

    @Valid
    @NotNull(message = "Client admin details required")
    private ClientAdminDTO clientAdmin;
}
