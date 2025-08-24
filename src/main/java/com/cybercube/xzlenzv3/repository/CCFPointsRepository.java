package com.cybercube.xzlenzv3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cybercube.xzlenzv3.model.CCFPoints.CCFPoint;

public interface CCFPointsRepository
        extends JpaRepository<CCFPoint, Long>, JpaSpecificationExecutor<CCFPoint> {

    // Basic lookups
    List<CCFPoint> findAllByCcfVersion_Id(Long ccfVersionId);
    Page<CCFPoint> findAllByCcfVersion_Id(Long ccfVersionId, Pageable pageable);

    Optional<CCFPoint> findByCcfVersion_IdAndCcfNumber(Long ccfVersionId, String ccfNumber);

    boolean existsByCcfVersion_IdAndCcfNumberIgnoreCase(Long ccfVersionId, String ccfNumber);

    // Eager fetch helpers when you need mappings in one go
    @EntityGraph(attributePaths = {
            "ccfVersion",
            "ccfPrinciple",
            "evidenceMappings",
            "privacyMappings",
            "riskMappings",
            "threatMappings",
            "complianceMappings"
    })
    Optional<CCFPoint> findWithAllMappingsById(Long id);

    @EntityGraph(attributePaths = {
            "ccfVersion",
            "ccfPrinciple"
    })
    Page<CCFPoint> findWithBasicsByCcfVersion_Id(Long ccfVersionId, Pageable pageable);
}
