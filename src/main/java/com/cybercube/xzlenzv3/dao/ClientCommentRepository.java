package com.cybercube.xzlenzv3.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cybercube.xzlenzv3.model.ClientComment.ClientComment;

public interface ClientCommentRepository extends JpaRepository<ClientComment, Integer> {

    // By Client CCF Point
    List<ClientComment> findByClientCCFPoint_IdOrderByCreatedAtDesc(Long clientCcfPointId);
    Page<ClientComment> findByClientCCFPoint_Id(Long clientCcfPointId, Pageable pageable);
    long countByClientCCFPoint_Id(Long clientCcfPointId);
    void deleteAllByClientCCFPoint_Id(Long clientCcfPointId);
    Optional<ClientComment> findTopByClientCCFPoint_IdOrderByCreatedAtDesc(Long clientCcfPointId);

    // By Client Compliance Point
    List<ClientComment> findByClientCompliancePoint_IdOrderByCreatedAtDesc(Long clientCompliancePointId);
    Page<ClientComment> findByClientCompliancePoint_Id(Long clientCompliancePointId, Pageable pageable);
    long countByClientCompliancePoint_Id(Long clientCompliancePointId);
    void deleteAllByClientCompliancePoint_Id(Long clientCompliancePointId);
    Optional<ClientComment> findTopByClientCompliancePoint_IdOrderByCreatedAtDesc(Long clientCompliancePointId);

    // By creator (useful for audits)
    Page<ClientComment> findByCreatedBy_Id(Long userId, Pageable pageable);

    // Time window (e.g., recent activity feed)
    Page<ClientComment> findByCreatedAtBetween(LocalDateTime from, LocalDateTime to, Pageable pageable);
}
