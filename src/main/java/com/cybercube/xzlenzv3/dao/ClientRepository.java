package com.cybercube.xzlenzv3.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cybercube.xzlenzv3.model.Client.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByCompanyNameIgnoreCase(String companyName);

    Page<Client> findByCompanyNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrCityContainingIgnoreCaseOrStateContainingIgnoreCase(
            String company, String email, String city, String state, Pageable pageable);

    @Query("select count(u) from User u where u.client.id = :clientId")
    long countUsersByClientId(Integer clientId);
}
