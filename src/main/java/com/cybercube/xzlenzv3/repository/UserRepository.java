package com.cybercube.xzlenzv3.repository;


import java.util.List;
import java.util.Optional;

import com.cybercube.xzlenzv3.model.Client.Client;
import com.cybercube.xzlenzv3.model.Client.dto.ClientResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cybercube.xzlenzv3.model.User.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    // Uniques / lookups
    Optional<User> findBySsoId(String ssoId);
    Optional<User> findByEmailIgnoreCase(String email);
    boolean existsBySsoId(String ssoId);
    boolean existsByEmailIgnoreCase(String email);

    // Search (name/email/SSO)
    Page<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrSsoIdContainingIgnoreCase(
            String firstName, String lastName, String email, String ssoId, Pageable pageable);

    // By client
    Page<User> findByClient_Id(Integer clientId, Pageable pageable);
    long countByClient_Id(Integer clientId);

    // By profile status (active/inactive/deleted)
    Page<User> findByProfileStatus(String profileStatus, Pageable pageable);

    // By role/profile type (e.g., "ADMIN", "CLIENT_USER", etc.)
    List<User> findByUserProfiles_TypeIgnoreCase(String type);
    Page<User> findByUserProfiles_TypeIgnoreCase(String type, Pageable pageable);

    List<User> findByClientId(Integer clientId);

    Optional<User> findFirstByClientAndDesignation(Client client, String designation);
}
