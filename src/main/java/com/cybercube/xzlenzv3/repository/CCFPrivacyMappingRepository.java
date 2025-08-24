package com.cybercube.xzlenzv3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybercube.xzlenzv3.model.DataPrivacyPrinciple.CCFPrivacyMapping;

public interface CCFPrivacyMappingRepository extends JpaRepository<CCFPrivacyMapping, Long> {

    // By CCF Point
    List<CCFPrivacyMapping> findByCcfPoint_Id(Long ccfPointId);
    long countByCcfPoint_Id(Long ccfPointId);
    void deleteAllByCcfPoint_Id(Long ccfPointId);

    // By Data Privacy Principle
    List<CCFPrivacyMapping> findByDataPrivacyPrinciple_Id(Long dataPrivacyId);
    long countByDataPrivacyPrinciple_Id(Long dataPrivacyId);
    void deleteAllByDataPrivacyPrinciple_Id(Long dataPrivacyId);

    // Unique mapping guard
    Optional<CCFPrivacyMapping> findByCcfPoint_IdAndDataPrivacyPrinciple_Id(Long ccfPointId, Long dataPrivacyId);
    boolean existsByCcfPoint_IdAndDataPrivacyPrinciple_Id(Long ccfPointId, Long dataPrivacyId);
}
