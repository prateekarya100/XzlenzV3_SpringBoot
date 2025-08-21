package com.cybercube.xzlenzv3.model.Client.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateUserRequest {
    private String ssoId;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private Integer clientId;
    private List<String> roles;
}
