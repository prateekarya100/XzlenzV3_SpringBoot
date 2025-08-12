package com.cybercube.xzlenzv3.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybercube.xzlenzv3.model.EvidenceRequestList.CCFEvidenceMapping;

public interface CCFEvidenceMappingRepository extends JpaRepository<CCFEvidenceMapping, Long> {

    // By CCF Point
    List<CCFEvidenceMapping> findByCcfPoint_Id(Long ccfPointId);
    long countByCcfPoint_Id(Long ccfPointId);
    void deleteAllByCcfPoint_Id(Long ccfPointId);

    // By Evidence Request
    List<CCFEvidenceMapping> findByEvidenceRequest_Id(Long evidenceRequestId);
    long countByEvidenceRequest_Id(Long evidenceRequestId);
    void deleteAllByEvidenceRequest_Id(Long evidenceRequestId);

    // Unique mapping guard
    Optional<CCFEvidenceMapping> findByCcfPoint_IdAndEvidenceRequest_Id(Long ccfPointId, Long evidenceRequestId);
    boolean existsByCcfPoint_IdAndEvidenceRequest_Id(Long ccfPointId, Long evidenceRequestId);
}
