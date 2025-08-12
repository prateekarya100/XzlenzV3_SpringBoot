package com.cybercube.xzlenzv3.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cybercube.xzlenzv3.model.DataPrivacyPrinciple.DataPrivacyPrinciple;

public interface DataPrivacyPrincipleRepository extends JpaRepository<DataPrivacyPrinciple, Long> {

    Optional<DataPrivacyPrinciple> findByPrincipleNameIgnoreCase(String principleName);
    boolean existsByPrincipleNameIgnoreCase(String principleName);

    Page<DataPrivacyPrinciple> findByPrincipleNameContainingIgnoreCaseOrScfDpmpDescriptionContainingIgnoreCase(
            String nameLike, String descLike, Pageable pageable);

    long countByCcfMappings_DataPrivacyPrinciple_Id(Long dataPrivacyId);
    long countByComplianceMappings_DataPrivacyPrinciple_Id(Long dataPrivacyId);
}
