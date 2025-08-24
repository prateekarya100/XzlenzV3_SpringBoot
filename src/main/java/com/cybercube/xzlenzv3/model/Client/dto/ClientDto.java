package com.cybercube.xzlenzv3.dto;

import lombok.Data;

@Data
public class ClientDto {
    private Integer id;
    private String companyName;
    private String contactNumber;
    private String email;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String companyUrl;
    private String imageUrl;
    private String createdBy;
    private String updatedBy;
}
