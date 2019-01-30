package com.westbank.repository;

import com.westbank.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    @Transactional(readOnly = true)
    Optional<Role> findById(@Nullable String id);

}
