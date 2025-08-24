package com.cybercube.xzlenzv3.config;

import com.cybercube.xzlenzv3.config.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
<<<<<<< HEAD
                        .requestMatchers("/api/super-admin/create").permitAll()
                        .requestMatchers("/api/clients/**").hasAnyRole("SUPER_ADMIN", "CLIENT_ADMIN")
                        .requestMatchers("/api/client/**").hasRole("SUPER_ADMIN")
                        .requestMatchers("/api/client/**").hasAnyRole("SUPER_ADMIN", "CLIENT_ADMIN")
                        .requestMatchers("/api/client/users/**").hasAnyRole("CLIENT_ADMIN", "CLIENT_USER")
=======
                        .requestMatchers("/api/clients/**").hasAnyRole("SUPER_ADMIN", "CLIENT_ADMIN")
>>>>>>> eefcff17c2301b814b811c6e70abcafa77d77b84
                        .requestMatchers("/api/users/**").hasAnyRole("SUPER_ADMIN", "CLIENT_ADMIN")
                        .requestMatchers("/api/profile/**").hasAnyRole("CLIENT_USER", "CLIENT_ADMIN")
                        .anyRequest().authenticated()
                )
<<<<<<< HEAD
                .httpBasic();
=======
                .httpBasic(); // Basic Auth for now
>>>>>>> eefcff17c2301b814b811c6e70abcafa77d77b84

        return http.build();
    }

<<<<<<< HEAD

=======
>>>>>>> eefcff17c2301b814b811c6e70abcafa77d77b84
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
