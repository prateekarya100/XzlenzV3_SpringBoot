package com.cybercube.xzlenzv3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import com.cybercube.xzlenzv3.model.ClientProject.ClientProject;

public interface ClientProjectRepository extends JpaRepository<ClientProject, Long>, JpaSpecificationExecutor<ClientProject> {

    // --- By client ---
    List<ClientProject> findByClient_Id(Integer clientId);
    Page<ClientProject> findByClient_Id(Integer clientId, Pageable pageable);
    void deleteAllByClient_Id(Integer clientId);
    long countByClient_Id(Integer clientId);

    // Unique / lookup per client
    Optional<ClientProject> findByClient_IdAndNameIgnoreCase(Integer clientId, String name);
    boolean existsByClient_IdAndNameIgnoreCase(Integer clientId, String name);

    // Search
    Page<ClientProject> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
    Page<ClientProject> findByClient_IdAndNameContainingIgnoreCase(Integer clientId, String keyword, Pageable pageable);


    // Eager fetch helper (when you need versions/compliances together)
    @EntityGraph(attributePaths = { "ccfVersions", "compliances" })
    Optional<ClientProject> findWithAssignmentsById(Long id);

    // Counts of linked assignments
    @Query("select count(v) from ClientCCFVersion v where v.project.id = :projectId")
    long countCcfVersions(Long projectId);

    @Query("select count(c) from ClientComplianceFramework c where c.project.id = :projectId")
    long countCompliances(Long projectId);
}
