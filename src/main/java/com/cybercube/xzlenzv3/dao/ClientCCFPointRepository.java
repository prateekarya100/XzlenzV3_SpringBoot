package com.cybercube.xzlenzv3.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cybercube.xzlenzv3.model.ClientCCFPoint.ClientCCFPoint;

public interface ClientCCFPointRepository extends JpaRepository<ClientCCFPoint, Long> {

    Optional<ClientCCFPoint> findByCcfNumberIgnoreCase(String ccfNumber);

    boolean existsByCcfNumberIgnoreCase(String ccfNumber);

    List<ClientCCFPoint> findByInScopeTrue();

    Page<ClientCCFPoint> findByInScope(Boolean inScope, Pageable pageable);
}
