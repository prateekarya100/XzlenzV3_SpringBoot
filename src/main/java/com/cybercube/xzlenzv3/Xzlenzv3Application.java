package com.cybercube.xzlenzv3;

import com.cybercube.xzlenzv3.model.User.User;
import com.cybercube.xzlenzv3.model.User.UserProfile;
import com.cybercube.xzlenzv3.model.User.UserProfileType;
import com.cybercube.xzlenzv3.repository.UserProfileRepository;
import com.cybercube.xzlenzv3.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableJpaRepositories(basePackages = "com.cybercube.xzlenzv3.repository")
@EntityScan(basePackages = "com.cybercube.xzlenzv3.model")
@ComponentScan(basePackages = "com.cybercube.xzlenzv3")
@SpringBootApplication(scanBasePackages = "com.cybercube.xzlenzv3")
public class Xzlenzv3Application {

	public static void main(String[] args) {
		SpringApplication.run(Xzlenzv3Application.class, args);
	}

	@Bean
	public CommandLineRunner initSuperUser(UserRepository userRepo,
										   UserProfileRepository profileRepo,
										   PasswordEncoder passwordEncoder) {
		return args -> {
			String ssoId = "super001";
			String rawPassword = "Super@123";

			UserProfile superProfile = profileRepo.findByType(UserProfileType.SUPER.getUserProfileType())
					.orElseGet(() -> {
						UserProfile newProfile = UserProfile.builder()
								.type(UserProfileType.SUPER.getUserProfileType())
								.build();
						return profileRepo.save(newProfile);
					});

			if (userRepo.findBySsoId(ssoId).isEmpty()) {
				User superUser = User.builder()
						.ssoId(ssoId)
						.password(passwordEncoder.encode(rawPassword))
						.firstName("Super")
						.lastName("Admin")
						.email("super@admin.com")
						.designation("SUPER")
						.contactNumber("+911234567890")
						.profileStatus("active")
						.createdAt(java.time.LocalDateTime.now())
						.build();

				superUser.getUserProfiles().add(superProfile);

				userRepo.save(superUser);
				System.out.println("✅ SUPER user created with profile mapping: " + ssoId);
			} else {
				System.out.println("ℹ️ SUPER user already exists: " + ssoId);
			}
		};
	}


}
