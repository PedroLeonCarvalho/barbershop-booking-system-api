package com.secured_template.repository;

import com.secured_template.domain.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository <Privilege, Long> {
    Privilege findByName(String name);
}
