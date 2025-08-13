package com.cybercube.xzlenzv3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import com.cybercube.xzlenzv3.model.Compliance.CompliancePoint;

public interface CompliancePointRepository extends JpaRepository<CompliancePoint, Long> {

    // Within a framework
    List<CompliancePoint> findByFramework_Id(Long frameworkId);
    Page<CompliancePoint> findByFramework_Id(Long frameworkId, Pageable pageable);

    // Unique guard per framework
    Optional<CompliancePoint> findByFramework_IdAndControlNumberIgnoreCase(Long frameworkId, String controlNumber);
    boolean existsByFramework_IdAndControlNumberIgnoreCase(Long frameworkId, String controlNumber);

    // Milestone filters
    Page<CompliancePoint> findByFramework_IdAndMilestone_Id(Long frameworkId, Long milestoneId, Pageable pageable);
    long countByMilestone_Id(Long milestoneId);

    // Keyword search
    Page<CompliancePoint> findByFramework_IdAndControlNumberContainingIgnoreCaseOrFramework_IdAndDescriptionContainingIgnoreCase(
            Long frameworkIdForControl, String controlLike,
            Long frameworkIdForDesc, String descLike,
            Pageable pageable);

    // Maintenance helpers
    long countByFramework_Id(Long frameworkId);
    void deleteAllByFramework_Id(Long frameworkId);
}
