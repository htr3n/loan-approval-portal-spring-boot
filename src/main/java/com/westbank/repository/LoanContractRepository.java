package com.westbank.repository;

import com.westbank.entity.LoanContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanContractRepository extends JpaRepository<LoanContract, Long> {

    @Transactional(readOnly = true)
    Optional<LoanContract> findById(@Nullable Long id);

    @Transactional(readOnly = true)
    @Query("SELECT lc FROM LoanContract lc LEFT JOIN FETCH lc.loanFile LEFT JOIN FETCH lc.agency")
    List<LoanContract> findAllWithFetch();
}
