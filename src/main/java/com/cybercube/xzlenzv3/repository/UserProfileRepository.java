package com.cybercube.xzlenzv3.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybercube.xzlenzv3.model.User.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

    Optional<UserProfile> findByTypeIgnoreCase(String type);
    boolean existsByTypeIgnoreCase(String type);

    // Useful for seeding or fetching multiple roles
    List<UserProfile> findByTypeIn(Collection<String> types);

//    UserProfile findByType(String userProfileType);

//    UserProfile findByRoleName(String roleName);

    Optional<UserProfile> findByType(String type);

}
