package com.cybercube.xzlenzv3.model.Client;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.validator.constraints.URL;

import com.cybercube.xzlenzv3.model.CCFPoints.CCFPoint;
import com.cybercube.xzlenzv3.model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
	Integer id;
	
	@Column(name = "COMPANY_NAME")
	@NotBlank(message = "Company name cannot be empty (Company name cannot be longer than 40 characters)")
	@Size(max = 40, message = "Company name cannot be longer than 40 characters")
	String companyName;
	
	@Column(name = "CONTACT_NUMBER", columnDefinition = "varchar(20) default ''")
	@NotBlank(message = "Contact number cannot be empty (Invalid phone number. It should be between 8 and 15 digits.)")
	@Pattern(regexp = "^[0-9]{8,15}$", message = "Invalid phone number. It should be between 8 and 15 digits.")
	String contactNumber;
	
	@NotEmpty(message = "Email cannot be empty (Email cannot be longer than 100 characters)")
	@Size(max = 100, message = "Email cannot be longer than 100 characters")
    @Email(message = "Invalid email format")
	@Column(name="EMAIL", nullable=false)
	private String email;
	
	@Column(name = "ADDRESS", columnDefinition = "varchar(50) default ''")
	@Size(max = 50, message = "Address cannot be longer than 50 characters")
	String address;
	
	@Column(name = "CITY", columnDefinition = "varchar(20) default ''")
	@Size(max = 20, message = "City cannot be longer than 20 characters")
	String city;
	
	@Column(name = "STATE", columnDefinition = "varchar(20) default ''")
	@Size(max = 20, message = "State cannot be longer than 20 characters")
	String state;
	
	@Column(name = "COUNTRY", columnDefinition = "varchar(20) default ''")
	@Size(max = 20, message = "Country cannot be longer than 20 characters")
	String country;
	
	@Column(name = "ZIP_CODE", columnDefinition = "varchar(20) default ''")
	@Size(max = 8, message = "Zip code cannot be longer than 8 characters")
	String zipCode;
	
	@Size(max = 50, message = "CompanyUrl cannot be longer than 50 characters")
	@Column(name = "COMPANY_URL", columnDefinition = "varchar(50) default ''")
	@URL(message = "Invalid URL format")
	String companyUrl;
	
	@Column(name = "image_url")
	String imageUrl;
	
	@ManyToOne
    private User createdBy;

	@ManyToOne
    private User updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> clientUser;

}
