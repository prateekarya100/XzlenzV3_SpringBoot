package com.cybercube.xzlenzv3.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import com.cybercube.xzlenzv3.model.Compliance.ComplianceFramework;

public interface ComplianceFrameworkRepository extends JpaRepository<ComplianceFramework, Long> {

    Optional<ComplianceFramework> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);

    Page<ComplianceFramework> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

    @Query("select count(p) from CompliancePoint p where p.framework.id = :frameworkId")
    long countPointsByFrameworkId(Long frameworkId);
}
