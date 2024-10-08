package com.secured_template.repository;

import com.secured_template.domain.BarberService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberServiceRepository extends JpaRepository <BarberService, Long> {



    BarberService findServiceById(Long serviceId);
}
