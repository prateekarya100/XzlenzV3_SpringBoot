package com.cybercube.xzlenzv3.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cybercube.xzlenzv3.model.ClientCompliance.ClientComplianceFramework;

public interface ClientComplianceFrameworkRepository extends JpaRepository<ClientComplianceFramework, Long> {

    Optional<ClientComplianceFramework> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);

    Page<ClientComplianceFramework> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

    // How many points are attached to a framework (useful before delete)
    @Query("select count(p) from ClientCompliancePoint p where p.framework.id = :frameworkId")
    long countPointsByFrameworkId(Long frameworkId);
}
