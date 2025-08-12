package com.cybercube.xzlenzv3.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cybercube.xzlenzv3.model.ThreatCatalog.ThreatCatalog;

public interface ThreatCatalogRepository extends JpaRepository<ThreatCatalog, Long> {

    Optional<ThreatCatalog> findByThreatNumberIgnoreCase(String threatNumber);
    boolean existsByThreatNumberIgnoreCase(String threatNumber);

    Page<ThreatCatalog> findByThreatNumberContainingIgnoreCaseOrThreatTitleContainingIgnoreCaseOrThreatDescriptionContainingIgnoreCaseOrThreatGroupingContainingIgnoreCase(
            String number, String title, String description, String grouping, Pageable pageable);

    long countByCcfMappings_Threat_Id(Long threatId);
}
