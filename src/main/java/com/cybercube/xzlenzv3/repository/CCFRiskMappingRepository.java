package com.cybercube.xzlenzv3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybercube.xzlenzv3.model.RiskCatalog.CCFRiskMapping;

public interface CCFRiskMappingRepository extends JpaRepository<CCFRiskMapping, Long> {

    // By CCF Point
    List<CCFRiskMapping> findByCcfPoint_Id(Long ccfPointId);
    long countByCcfPoint_Id(Long ccfPointId);
    void deleteAllByCcfPoint_Id(Long ccfPointId);

    // By Risk
    List<CCFRiskMapping> findByRisk_Id(Long riskId);
    long countByRisk_Id(Long riskId);
    void deleteAllByRisk_Id(Long riskId);

    // Unique guard
    Optional<CCFRiskMapping> findByCcfPoint_IdAndRisk_Id(Long ccfPointId, Long riskId);
    boolean existsByCcfPoint_IdAndRisk_Id(Long ccfPointId, Long riskId);
}
