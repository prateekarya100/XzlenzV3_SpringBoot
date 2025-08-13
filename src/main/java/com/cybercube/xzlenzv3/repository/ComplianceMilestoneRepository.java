package com.cybercube.xzlenzv3.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import com.cybercube.xzlenzv3.model.Compliance.ComplianceMilestone;

public interface ComplianceMilestoneRepository extends JpaRepository<ComplianceMilestone, Long> {

    // Per-framework listings
    List<ComplianceMilestone> findByFramework_IdOrderByIdAsc(Long frameworkId);
    Page<ComplianceMilestone> findByFramework_Id(Long frameworkId, Pageable pageable);

    // Optional uniqueness per framework (milestone label)
    boolean existsByFramework_IdAndMilestoneIgnoreCase(Long frameworkId, String milestone);

    // Search
    Page<ComplianceMilestone> findByFramework_IdAndMilestoneContainingIgnoreCase(Long frameworkId, String keyword, Pageable pageable);

    // Counts
    @Query("select count(p) from CompliancePoint p where p.milestone.id = :milestoneId")
    long countPointsByMilestoneId(Long milestoneId);
}
