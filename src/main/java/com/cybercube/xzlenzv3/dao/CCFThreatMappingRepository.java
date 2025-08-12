package com.cybercube.xzlenzv3.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybercube.xzlenzv3.model.ThreatCatalog.CCFThreatMapping;

public interface CCFThreatMappingRepository extends JpaRepository<CCFThreatMapping, Long> {

    // By CCF Point
    List<CCFThreatMapping> findByCcfPoint_Id(Long ccfPointId);
    long countByCcfPoint_Id(Long ccfPointId);
    void deleteAllByCcfPoint_Id(Long ccfPointId);

    // By Threat
    List<CCFThreatMapping> findByThreat_Id(Long threatId);
    long countByThreat_Id(Long threatId);
    void deleteAllByThreat_Id(Long threatId);

    // Unique guard
    Optional<CCFThreatMapping> findByCcfPoint_IdAndThreat_Id(Long ccfPointId, Long threatId);
    boolean existsByCcfPoint_IdAndThreat_Id(Long ccfPointId, Long threatId);
}
