package com.cybercube.xzlenzv3.model.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.cybercube.xzlenzv3.model.Client.Client;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable{
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	private String ssoId;
	
	@Size(max = 50, message = "Designation must not exceed 50 characters")
	@Pattern(
		    regexp = "^[A-Za-z0-9_.,&\\- ]*$",
		    message = "Must contain only letters, numbers, spaces, underscores (_), hyphens (-), or periods (.)"
		)
	String designation;
	

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotEmpty(message = "Password cannot be empty")
	@Size(max = 100, message = "Password cannot be too longer")
	private String password;
		
	@NotEmpty(message = "First Name cannot be empty")
	@Size(max = 15, message = "First Name cannot be longer than 15 characters")
	@Pattern(regexp = "^[A-Za-z0-9 ]*$", message = "Must contain only letters, numbers, spaces")
	private String firstName;

	@NotEmpty(message = "Last Name cannot be empty")
	@Size(max = 15, message = "Last Name cannot be longer than 15 characters")
	@Pattern(regexp = "^[A-Za-z0-9 ]*$", message = "Must contain only letters, numbers, spaces")
	private String lastName;

	@Size(max = 50, message = "Email cannot be longer than 50 characters")
	@NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
	private String email;
	
	@NotEmpty(message = "Contact number cannot be empty")
	@Pattern(
    	    regexp = "^\\+?[0-9]{1,4}?[0-9]{8,15}$",
    	    message = "Invalid phone number. It should be between 8 and 15 digits, and may include a country code like +91."
    	)
	String contactNumber;
	
	@Column(name = "image_url")
	String imageUrl;
	
	
	@Pattern(regexp = "^(active|inactive|deleted)$", message = "Invalid profileStatus")
	String profileStatus;
	
	@ManyToOne
    private User createdBy;

	@ManyToOne
    private User updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    
 // make the association optional + nullable at the column
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(
        name = "client_id",
        nullable = true,
        foreignKey = @ForeignKey(name = "fk_client_user_client")
    )
    private Client client; // can be null


	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_user_profile", 
             joinColumns = { @JoinColumn(name = "USER_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
	private Set<UserProfile> userProfiles = new HashSet<UserProfile>();



	
}
