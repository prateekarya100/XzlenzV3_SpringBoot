package com.cybercube.xzlenzv3.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cybercube.xzlenzv3.model.ClientCCFPoint.ClientCCFVersion;

public interface ClientCCFVersionRepository extends JpaRepository<ClientCCFVersion, Long> {

    // By project
    List<ClientCCFVersion> findByProject_Id(Long projectId);
    Page<ClientCCFVersion> findByProject_Id(Long projectId, Pageable pageable);
    long countByProject_Id(Long projectId);
    void deleteAllByProject_Id(Long projectId);

    // Lookup / uniqueness within a project
    Optional<ClientCCFVersion> findByProject_IdAndVersionNameIgnoreCase(Long projectId, String versionName);
    boolean existsByProject_IdAndVersionNameIgnoreCase(Long projectId, String versionName);

    // Active on a given date (start <= date <= end)
    List<ClientCCFVersion> findByProject_IdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Long projectId, LocalDate dateOnOrAfterStart, LocalDate dateOnOrBeforeEnd);

    // Overlap check for a proposed window [startDate, endDate]
    @Query("""
        select case when count(c) > 0 then true else false end
        from ClientCCFVersion c
        where c.project.id = :projectId
          and c.startDate <= :endDate
          and (c.endDate is null or c.endDate >= :startDate)
    """)
    boolean existsOverlappingWindow(Long projectId, LocalDate startDate, LocalDate endDate);

    // Same as above but excluding a record (for updates)
    @Query("""
        select case when count(c) > 0 then true else false end
        from ClientCCFVersion c
        where c.project.id = :projectId
          and c.id <> :excludeId
          and c.startDate <= :endDate
          and (c.endDate is null or c.endDate >= :startDate)
    """)
    boolean existsOverlappingWindowExcludingId(Long projectId, Long excludeId, LocalDate startDate, LocalDate endDate);

    // Latest by start date
    Optional<ClientCCFVersion> findTopByProject_IdOrderByStartDateDesc(Long projectId);
}
