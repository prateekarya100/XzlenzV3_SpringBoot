package com.cybercube.xzlenzv3.model.User;

import jakarta.persistence.*;
import lombok.*;

<<<<<<< HEAD
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
=======
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="user_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile implements Serializable{
>>>>>>> eefcff17c2301b814b811c6e70abcafa77d77b84

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "TYPE", length = 20, unique = true, nullable = false)
	private String type = UserProfileType.CLIENT_USER.getUserProfileType();
<<<<<<< HEAD
=======



>>>>>>> eefcff17c2301b814b811c6e70abcafa77d77b84

	@ManyToMany(mappedBy = "userProfiles")
	private Set<User> users;

	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", type=" + type + "]";
	}
}
