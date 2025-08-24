package com.cybercube.xzlenzv3.model.User.dto;

import com.cybercube.xzlenzv3.model.User.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientWithUsersDTO {
    private Integer clientId;
    private String clientName;
    private List<UserDTO> clientUsers;
}
