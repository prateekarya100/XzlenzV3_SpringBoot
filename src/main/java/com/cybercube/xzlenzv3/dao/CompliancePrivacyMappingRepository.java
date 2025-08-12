package com.cybercube.xzlenzv3.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybercube.xzlenzv3.model.DataPrivacyPrinciple.CompliancePrivacyMapping;

public interface CompliancePrivacyMappingRepository extends JpaRepository<CompliancePrivacyMapping, Long> {

    // By Compliance Point
    List<CompliancePrivacyMapping> findByCompliancePoint_Id(Long compliancePointId);
    long countByCompliancePoint_Id(Long compliancePointId);
    void deleteAllByCompliancePoint_Id(Long compliancePointId);

    // By Data Privacy Principle
    List<CompliancePrivacyMapping> findByDataPrivacyPrinciple_Id(Long dataPrivacyId);
    long countByDataPrivacyPrinciple_Id(Long dataPrivacyId);
    void deleteAllByDataPrivacyPrinciple_Id(Long dataPrivacyId);

    // Unique mapping guard
    Optional<CompliancePrivacyMapping> findByDataPrivacyPrinciple_IdAndCompliancePoint_Id(Long dataPrivacyId, Long compliancePointId);
    boolean existsByDataPrivacyPrinciple_IdAndCompliancePoint_Id(Long dataPrivacyId, Long compliancePointId);
}
