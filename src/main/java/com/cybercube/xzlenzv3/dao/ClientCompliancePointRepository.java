package com.cybercube.xzlenzv3.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cybercube.xzlenzv3.model.ClientCompliance.ClientCompliancePoint;

public interface ClientCompliancePointRepository extends JpaRepository<ClientCompliancePoint, Long> {

    // Lookups within a framework
    List<ClientCompliancePoint> findByFramework_Id(Long frameworkId);
    Page<ClientCompliancePoint> findByFramework_Id(Long frameworkId, Pageable pageable);

    // Unique guard per framework
    Optional<ClientCompliancePoint> findByFramework_IdAndControlNumberIgnoreCase(Long frameworkId, String controlNumber);
    boolean existsByFramework_IdAndControlNumberIgnoreCase(Long frameworkId, String controlNumber);

    // Scope filters
    Page<ClientCompliancePoint> findByFramework_IdAndInScope(Long frameworkId, Boolean inScope, Pageable pageable);
    List<ClientCompliancePoint> findByFramework_IdAndInScopeTrue(Long frameworkId);

    // Keyword search on control number
    Page<ClientCompliancePoint> findByFramework_IdAndControlNumberContainingIgnoreCase(Long frameworkId, String keyword, Pageable pageable);

    // Maintenance helpers
    long countByFramework_Id(Long frameworkId);
    void deleteAllByFramework_Id(Long frameworkId);
}
