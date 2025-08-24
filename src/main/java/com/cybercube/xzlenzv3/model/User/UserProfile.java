package com.cybercube.xzlenzv3.model.User;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "user_profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "TYPE", length = 20, unique = true, nullable = false)
	private String type = UserProfileType.CLIENT_USER.getUserProfileType();

	@ManyToMany(mappedBy = "userProfiles")
	private Set<User> users;

	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", type=" + type + "]";
	}
}
