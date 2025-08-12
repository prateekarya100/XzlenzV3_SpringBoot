package com.cybercube.xzlenzv3.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cybercube.xzlenzv3.model.ClientEvidence.ClientEvidenceFile;

public interface ClientEvidenceFileRepository extends JpaRepository<ClientEvidenceFile, Integer> {

    // --- Lookups by association ---
    Page<ClientEvidenceFile> findByClientCCFPoint_Id(Long clientCcfPointId, Pageable pageable);
    List<ClientEvidenceFile> findByClientCCFPoint_Id(Long clientCcfPointId);

    Page<ClientEvidenceFile> findByClientCompliancePoint_Id(Long clientCompliancePointId, Pageable pageable);
    List<ClientEvidenceFile> findByClientCompliancePoint_Id(Long clientCompliancePointId);

    long countByClientCCFPoint_Id(Long clientCcfPointId);
    long countByClientCompliancePoint_Id(Long clientCompliancePointId);

    void deleteAllByClientCCFPoint_Id(Long clientCcfPointId);
    void deleteAllByClientCompliancePoint_Id(Long clientCompliancePointId);

    Optional<ClientEvidenceFile> findTopByClientCCFPoint_IdOrderByCreatedAtDesc(Long clientCcfPointId);
    Optional<ClientEvidenceFile> findTopByClientCompliancePoint_IdOrderByCreatedAtDesc(Long clientCompliancePointId);

    // --- Search / filters ---
    Page<ClientEvidenceFile> findBySubjectContainingIgnoreCaseOrFileNameContainingIgnoreCase(
            String subject, String fileName, Pageable pageable);

    Page<ClientEvidenceFile> findByCreatedBy_Id(Long userId, Pageable pageable);

    Page<ClientEvidenceFile> findByCreatedAtBetween(LocalDateTime from, LocalDateTime to, Pageable pageable);

    // --- Duplicate guards (optional) ---
    boolean existsByClientCCFPoint_IdAndFileNameIgnoreCase(Long clientCcfPointId, String fileName);
    boolean existsByClientCompliancePoint_IdAndFileNameIgnoreCase(Long clientCompliancePointId, String fileName);
}
