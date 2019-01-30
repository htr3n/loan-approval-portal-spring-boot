package com.westbank.repository;

import com.westbank.entity.LoanFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanFileRepository extends JpaRepository<LoanFile, String> {

    @Transactional(readOnly = true)
    Optional<LoanFile> findById(@Nullable String id);

    @Transactional(readOnly = true)
    @Query("SELECT l FROM LoanFile l LEFT JOIN l.borrower c ON c.id = :borrowerId")
    List<LoanFile> findAllByBorrowerId(Long borrowerId);

    @Transactional(readOnly = true)
    @Query("SELECT l FROM LoanFile l LEFT JOIN l.borrower c ON c.id = :customerId WHERE l.status=7")
    List<LoanFile> findGrantedLoanFileByCustomer(Long customerId);

    @Transactional(readOnly = true)
    @Query("SELECT lf FROM LoanFile lf LEFT JOIN FETCH lf.borrower LEFT JOIN FETCH lf.coBorrower")
    List<LoanFile> findAllWithFetch();
}
