package com.secured_template.repository;

import com.secured_template.domain.BarberService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarberServiceRepository extends JpaRepository <BarberService, Long> {



    BarberService findServiceById(Long serviceId);

    List<BarberService>  findAllByisActiveTrue();
}
