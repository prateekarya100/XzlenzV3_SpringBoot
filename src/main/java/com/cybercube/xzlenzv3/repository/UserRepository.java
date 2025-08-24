package com.cybercube.xzlenzv3.repository;


import java.util.List;
import java.util.Optional;

import com.cybercube.xzlenzv3.model.Client.Client;
<<<<<<< HEAD
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
=======
import com.cybercube.xzlenzv3.model.Client.dto.ClientResponse;
>>>>>>> eefcff17c2301b814b811c6e70abcafa77d77b84
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cybercube.xzlenzv3.model.User.User;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
=======
>>>>>>> eefcff17c2301b814b811c6e70abcafa77d77b84

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
<<<<<<< HEAD

    @Query("SELECT u FROM User u JOIN FETCH u.client WHERE u.client = :client")
    List<User> findByClientWithClient(@Param("client") Client client);


    List<User> findByClient(Client client);


    // Find all users by client id
    List<User> findAllByClientId(Integer clientId);

    // Optional: for checking duplicates
    boolean existsBySsoIdAndClient(String ssoId, Client client);

    // Optional: for fetching single user
  Optional<User> findByIdAndClient(Long userId, Client client);

    void deleteAllByClientId(Long clientId);
=======
>>>>>>> eefcff17c2301b814b811c6e70abcafa77d77b84
}
