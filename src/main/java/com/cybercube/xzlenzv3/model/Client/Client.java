	package com.cybercube.xzlenzv3.model.Client;

	import java.io.Serializable;
	import java.time.LocalDateTime;
	import java.util.ArrayList;
	import java.util.List;

	import org.hibernate.validator.constraints.URL;

	import com.cybercube.xzlenzv3.model.User.User;
	import com.fasterxml.jackson.annotation.JsonIgnore;

	import jakarta.persistence.*;
	import jakarta.validation.constraints.Email;
	import jakarta.validation.constraints.NotBlank;
	import jakarta.validation.constraints.NotEmpty;
	import jakarta.validation.constraints.Pattern;
	import jakarta.validation.constraints.Size;
	import lombok.*;

	@Entity
	@Table(name = "clients")
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public class Client implements Serializable {

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
