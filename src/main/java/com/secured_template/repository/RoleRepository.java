package com.secured_template.repository;

import com.secured_template.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository <Role, Long> {
    Role findByName(String role);

    ;
}
