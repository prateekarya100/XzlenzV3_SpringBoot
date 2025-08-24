package com.cybercube.xzlenzv3.model.Client.dto;

import com.cybercube.xzlenzv3.dto.ClientDto;
import com.cybercube.xzlenzv3.model.Client.Client;
import com.cybercube.xzlenzv3.model.User.UserProfileType;

public class ClientMapper {

    public static ClientDto toDto(Client client) {
        if (client == null) return null;

        ClientDto dto = new ClientDto();
        dto.setId(client.getId());
        dto.setCompanyName(client.getCompanyName());
        dto.setContactNumber(client.getContactNumber());
        dto.setEmail(client.getEmail());
        dto.setAddress(client.getAddress());
        dto.setCity(client.getCity());
        dto.setState(client.getState());
        dto.setCountry(client.getCountry());
        dto.setZipCode(client.getZipCode());
        dto.setCompanyUrl(client.getCompanyUrl());
        dto.setImageUrl(client.getImageUrl());
        dto.setCreatedBy(UserProfileType.SUPER.getUserProfileType().toLowerCase());
        dto.setUpdatedBy(UserProfileType.SUPER.getUserProfileType().toLowerCase());

        return dto;
    }
}
