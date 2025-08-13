package com.cybercube.xzlenzv3.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cybercube.xzlenzv3.model.CCFPrinciple.CCFPrinciple;

public interface CCFPrincipleRepository extends JpaRepository<CCFPrinciple, Long> {

    Optional<CCFPrinciple> findByCcfIdentifierIgnoreCase(String ccfIdentifier);
    boolean existsByCcfIdentifierIgnoreCase(String ccfIdentifier);

    Page<CCFPrinciple> findByCcfIdentifierContainingIgnoreCaseOrCpPrincipleContainingIgnoreCaseOrCcfDomainContainingIgnoreCase(
            String idPart, String principlePart, String domainPart, Pageable pageable);

    @Query("""
        select count(p)
        from CCFPoint p
        where p.ccfPrinciple.id = :principleId
    """)
    long countPointsByPrincipleId(@Param("principleId") Long principleId);
}
