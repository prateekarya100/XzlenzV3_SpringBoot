package com.cybercube.xzlenzv3.model.Client.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientAdminResponse {
    private Integer id;
    private String username;
    private List<String> roles;
}
