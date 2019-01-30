package com.westbank.repository;

import com.westbank.entity.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, String> {
    @Transactional(readOnly = true)
    Optional<Agency> findById(@Nullable String id);
}
