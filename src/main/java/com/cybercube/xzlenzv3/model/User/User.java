package com.cybercube.xzlenzv3.model.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.cybercube.xzlenzv3.model.Client.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import lombok.*;

@Builder
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	@JsonIgnore
    private User createdBy;

	@ManyToOne
	@JsonIgnore
    private User updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
<<<<<<< HEAD
=======


 // make the association optional + nullable at the column
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(
        name = "client_id",
        nullable = true,
        foreignKey = @ForeignKey(name = "fk_client_user_client")
    )
    private Client client; // can be null
>>>>>>> eefcff17c2301b814b811c6e70abcafa77d77b84


 // make the association optional + nullable at the column
 @ManyToOne(fetch = FetchType.EAGER, optional = true)
 @JoinColumn(
		 name = "client_id",
		 nullable = true,
		 foreignKey = @ForeignKey(name = "fk_client_user_client")
 )
 private Client client; // Ignore during JSON serialization

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_user_profile",
<<<<<<< HEAD
			joinColumns = { @JoinColumn(name = "USER_ID") },
			inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
	@JsonIgnore
=======
             joinColumns = { @JoinColumn(name = "USER_ID") },
             inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
>>>>>>> eefcff17c2301b814b811c6e70abcafa77d77b84
	@Builder.Default
	private Set<UserProfile> userProfiles = new HashSet<UserProfile>();


	public boolean hasRole(String role) {
		return this.getUserProfiles().stream()
				.anyMatch(profile -> profile.getType().equalsIgnoreCase(role));
	}


<<<<<<< HEAD
	public boolean hasRole(String role) {
		System.out.println("Checking role: " + role);
		this.getUserProfiles().forEach(p -> System.out.println("User has profile: " + p.getType()));
		return this.getUserProfiles().stream()
				.anyMatch(profile -> profile.getType().equalsIgnoreCase(role));
	}



=======
>>>>>>> eefcff17c2301b814b811c6e70abcafa77d77b84
}
