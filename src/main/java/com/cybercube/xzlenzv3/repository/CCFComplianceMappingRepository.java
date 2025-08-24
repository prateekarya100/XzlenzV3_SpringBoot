package com.cybercube.xzlenzv3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybercube.xzlenzv3.model.CCFPoints.CCFComplianceMapping;

public interface CCFComplianceMappingRepository extends JpaRepository<CCFComplianceMapping, Long> {

    // Find all mappings for a given CCF point
    List<CCFComplianceMapping> findByCcfPoint_Id(Long ccfPointId);

    // Find all mappings for a given Compliance point
    List<CCFComplianceMapping> findByCompliancePoint_Id(Long compliancePointId);

    // Find a specific mapping
    Optional<CCFComplianceMapping> findByCcfPoint_IdAndCompliancePoint_Id(Long ccfPointId, Long compliancePointId);

    // Count mappings
    long countByCcfPoint_Id(Long ccfPointId);
    long countByCompliancePoint_Id(Long compliancePointId);

    // Delete all mappings for a CCF point
    void deleteAllByCcfPoint_Id(Long ccfPointId);

    // Delete all mappings for a Compliance point
    void deleteAllByCompliancePoint_Id(Long compliancePointId);
}
