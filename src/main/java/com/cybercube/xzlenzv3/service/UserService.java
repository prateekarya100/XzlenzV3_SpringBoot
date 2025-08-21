package com.cybercube.xzlenzv3.service;

import com.cybercube.xzlenzv3.exceptions.ResourceNotFoundException;
import com.cybercube.xzlenzv3.model.User.User;
import com.cybercube.xzlenzv3.model.User.UserProfile;
import com.cybercube.xzlenzv3.repository.UserProfileRepository;
import com.cybercube.xzlenzv3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository profileRepo;

    public UserProfile getProfileByType(String type) {
        return profileRepo.findByType(type)
                .orElseThrow(() -> new ResourceNotFoundException("UserProfile not found: " + type));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByEmailIgnoreCase(username)
                .or(() -> userRepository.findBySsoId(username))
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
    }

}
