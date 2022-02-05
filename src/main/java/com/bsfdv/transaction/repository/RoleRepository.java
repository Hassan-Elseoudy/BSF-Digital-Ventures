package com.bsfdv.transaction.repository;

import com.bsfdv.transaction.model.ERole;
import com.bsfdv.transaction.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}