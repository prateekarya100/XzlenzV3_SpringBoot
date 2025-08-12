package com.cybercube.xzlenzv3.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cybercube.xzlenzv3.model.RiskCatalog.RiskCatalog;

public interface RiskCatalogRepository extends JpaRepository<RiskCatalog, Long> {

    Optional<RiskCatalog> findByRiskNumberIgnoreCase(String riskNumber);
    boolean existsByRiskNumberIgnoreCase(String riskNumber);

    Page<RiskCatalog> findByRiskNumberContainingIgnoreCaseOrRiskTitleContainingIgnoreCaseOrRiskDescriptionContainingIgnoreCaseOrRiskGroupingContainingIgnoreCase(
            String number, String title, String description, String grouping, Pageable pageable);

    long countByCcfMappings_Risk_Id(Long riskId);
}
