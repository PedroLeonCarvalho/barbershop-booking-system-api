package com.secured_template.repository;

import com.secured_template.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {

    User findByEmail(String email);
    User findUserById(Long professionalId);

 @Query("SELECT u FROM User u JOIN u.roles r WHERE r.id = :roleIdOne OR r.id= :roleIdTwo")
    List<User> findUserByRoleId(@Param("roleIdOne") Long roleIdOne, @Param("roleIdTwo")Long roleIdTwo );
}
