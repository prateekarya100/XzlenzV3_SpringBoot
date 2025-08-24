	package com.cybercube.xzlenzv3.model.Client;

	import java.io.Serializable;
	import java.time.LocalDateTime;
	import java.util.ArrayList;
	import java.util.List;

<<<<<<< HEAD
	import org.hibernate.validator.constraints.URL;
=======
import com.cybercube.xzlenzv3.model.Client.dto.ClientResponse;
import org.hibernate.validator.constraints.URL;
>>>>>>> eefcff17c2301b814b811c6e70abcafa77d77b84

	import com.cybercube.xzlenzv3.model.User.User;
	import com.fasterxml.jackson.annotation.JsonIgnore;

	import jakarta.persistence.*;
	import jakarta.validation.constraints.Email;
	import jakarta.validation.constraints.NotBlank;
	import jakarta.validation.constraints.NotEmpty;
	import jakarta.validation.constraints.Pattern;
	import jakarta.validation.constraints.Size;
	import lombok.*;

<<<<<<< HEAD
	@Entity
	@Table(name = "clients")
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public class Client implements Serializable {
=======
@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
	public class Client extends ClientResponse implements Serializable {
	
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
>>>>>>> eefcff17c2301b814b811c6e70abcafa77d77b84

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "ID")
		private Integer id;

		@Column(name = "COMPANY_NAME", nullable = false, length = 40, unique = true)
		@NotBlank(message = "Company name cannot be empty")
		@Size(max = 40, message = "Company name cannot be longer than 40 characters")
		private String companyName;

		@Column(name = "CONTACT_NUMBER", length = 20)
		@NotBlank(message = "Contact number cannot be empty")
		@Pattern(regexp = "^[0-9]{8,15}$", message = "Invalid phone number. It should be between 8 and 15 digits.")
		private String contactNumber;

		@Column(name = "EMAIL", nullable = false, length = 100, unique = true)
		@NotEmpty(message = "Email cannot be empty")
		@Size(max = 100, message = "Email cannot be longer than 100 characters")
		@Email(message = "Invalid email format")
		private String email;

		@Column(name = "ADDRESS", length = 50)
		@Size(max = 50, message = "Address cannot be longer than 50 characters")
		private String address;

		@Column(name = "CITY", length = 20)
		@Size(max = 20, message = "City cannot be longer than 20 characters")
		private String city;

		@Column(name = "STATE", length = 20)
		@Size(max = 20, message = "State cannot be longer than 20 characters")
		private String state;

		@Column(name = "COUNTRY", length = 20)
		@Size(max = 20, message = "Country cannot be longer than 20 characters")
		private String country;

		@Column(name = "ZIP_CODE", length = 8)
		@Size(max = 8, message = "Zip code cannot be longer than 8 characters")
		private String zipCode;

		@Column(name = "COMPANY_URL", length = 50)
		@Size(max = 50, message = "Company URL cannot be longer than 50 characters")
		@URL(message = "Invalid URL format")
		private String companyUrl;

		@Column(name = "IMAGE_URL")
		private String imageUrl;

		@ManyToOne
		@JoinColumn(name = "CREATED_BY")
		private User createdBy;

		@ManyToOne
		@JoinColumn(name = "UPDATED_BY")
		private User updatedBy;

		@Column(name = "CREATED_AT")
		private LocalDateTime createdAt;

		@Column(name = "UPDATED_AT")
		private LocalDateTime updatedAt;

		@JsonIgnore
		@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
		private List<User> clientUser = new ArrayList<>();

	}
