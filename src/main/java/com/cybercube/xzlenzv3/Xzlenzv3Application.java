package com.cybercube.xzlenzv3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.cybercube.xzlenzv3.model")
@EnableJpaRepositories("com.cybercube.xzlenzv3.dao")
@SpringBootApplication
public class Xzlenzv3Application {

	public static void main(String[] args) {
		SpringApplication.run(Xzlenzv3Application.class, args);
	}

}
