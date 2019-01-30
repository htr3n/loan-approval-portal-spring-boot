package com.westbank.repository;

import com.westbank.entity.Role;
import com.westbank.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {

    @Transactional(readOnly = true)
    Optional<Staff> findById(@Nullable String id);

    @Transactional(readOnly = true)
    Optional<Staff> findByEmail(String email);

    @Transactional(readOnly = true)
    Optional<Staff> findByIdAndPassword(String id, String password);

    @Transactional(readOnly = true)
    Optional<Staff> findByEmailAndPassword(String email, String password);

    @Transactional(readOnly = true)
    Optional<Staff> findByIdOrEmail(String id, String email);

    @Transactional(readOnly = true)
    @Query("SELECT s FROM Staff s LEFT JOIN s.role r ON r.name = :role")
    List<Staff> findAllByRole(String role);

    @Transactional(readOnly = true)
    @Query("SELECT s FROM Staff s LEFT JOIN s.role r ON r.name = '" + Role.ID_CREDIT_BROKER + "'")
    List<Staff> getCreditBrokers();

    @Transactional(readOnly = true)
    @Query("SELECT s FROM Staff s LEFT JOIN s.role r ON r.name = '" + Role.ID_MANAGER + "'")
    List<Staff> getManagers();

    @Transactional(readOnly = true)
    @Query("SELECT s FROM Staff s LEFT JOIN s.role r ON r.name = '" + Role.ID_POST_PROCESSING_CLERK + "'")
    List<Staff> getPostProcessingClerks();

    @Transactional(readOnly = true)
    @Query("SELECT s FROM Staff s LEFT JOIN s.role r ON r.name = '" + Role.ID_SUPERVISOR + "'")
    List<Staff> getSupervisors();

    @Transactional(readOnly = true)
    @Query("SELECT s FROM Staff s LEFT JOIN FETCH s.role")
    List<Staff> findAllWithFetch();
}
