package com.westbank.repository;

import com.westbank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Transactional(readOnly = true)
    Optional<Customer> findById(@Nullable Long id);

    @Transactional(readOnly = true)
    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.address.country")
    List<Customer> findAllWithFetch();

    @Transactional(readOnly = true)
    @Query("SELECT c FROM Customer c WHERE c.email = :email")
    Optional<Customer> findByEmail(String email);

    @Transactional(readOnly = true)
    @Query("SELECT c FROM Customer c WHERE c.email = :email AND c.password = :password")
    Optional<Customer> findByEmailAndPassword(String email, String password);
}
