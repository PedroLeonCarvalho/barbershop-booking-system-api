package com.secured_template.repository;

import com.secured_template.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {

    User findByEmail(String email);

    User findByPhoneNumber(String phoneNumber);
}
