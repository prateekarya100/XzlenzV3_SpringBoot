package com.cybercube.xzlenzv3.config;

import com.cybercube.xzlenzv3.model.User.User;
import com.cybercube.xzlenzv3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepo.findBySsoId(ssoId);

        System.out.println("Searching for user with email: " + ssoId);
        if (userOpt.isEmpty()) {
            System.out.println("User not found in database!");
            throw new UsernameNotFoundException("User not found");
        }

        User user = userOpt.get();
        System.out.println("User found: " + user.getEmail());

        return new CustomUserDetails(user);
    }

}
