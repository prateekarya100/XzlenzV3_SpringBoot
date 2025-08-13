package com.cybercube.xzlenzv3.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import com.cybercube.xzlenzv3.model.CCFPoints.CCFVersion;

public interface CCFVersionRepository extends JpaRepository<CCFVersion, Long> {

    Optional<CCFVersion> findByVersionNameIgnoreCase(String versionName);
    boolean existsByVersionNameIgnoreCase(String versionName);

    Page<CCFVersion> findByVersionNameContainingIgnoreCase(String keyword, Pageable pageable);

    // JPQL: count points via the mapped association
    @Query("""
        select count(p)
        from CCFVersion v
        join v.ccfPoints p
        where v.id = :versionId
    """)
    long countPointsByVersionId(@Param("versionId") Long versionId);

    // Optional: fetch version with points eagerly (still JPQL)
    @Query("""
        select v
        from CCFVersion v
        left join fetch v.ccfPoints
        where v.id = :id
    """)
    Optional<CCFVersion> findByIdWithPoints(@Param("id") Long id);
}
