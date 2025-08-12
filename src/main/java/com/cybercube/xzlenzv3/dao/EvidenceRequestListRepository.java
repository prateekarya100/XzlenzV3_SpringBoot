package com.cybercube.xzlenzv3.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cybercube.xzlenzv3.model.EvidenceRequestList.EvidenceRequestList;

public interface EvidenceRequestListRepository extends JpaRepository<EvidenceRequestList, Long> {

    Optional<EvidenceRequestList> findByErlNumberIgnoreCase(String erlNumber);

    boolean existsByErlNumberIgnoreCase(String erlNumber);

    Page<EvidenceRequestList> findByErlNumberContainingIgnoreCaseOrAreaOfFocusContainingIgnoreCaseOrDocumentationArtifactContainingIgnoreCase(
            String erlNumber, String areaOfFocus, String documentationArtifact, Pageable pageable);

    long countByCcfMappings_EvidenceRequest_Id(Long evidenceRequestId);
}
