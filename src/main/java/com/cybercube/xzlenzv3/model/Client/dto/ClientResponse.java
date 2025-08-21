package com.cybercube.xzlenzv3.model.Client.dto;

import com.cybercube.xzlenzv3.model.Client.dto.ClientAdminResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {
    private Integer id;
    private String companyName;
    private String email;
    private ClientAdminResponse clientAdmin;
}
