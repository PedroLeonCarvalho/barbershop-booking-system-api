package com.secured_template.infra.security.roles;

import com.secured_template.domain.Privilege;
import com.secured_template.domain.Role;
import com.secured_template.domain.User;
import com.secured_template.repository.PrivilegeRepository;
import com.secured_template.repository.RoleRepository;
import com.secured_template.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {


        boolean alreadySetup = false;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private RoleRepository roleRepository;

        @Autowired
        private PrivilegeRepository privilegeRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        @Transactional
        public void onApplicationEvent (ContextRefreshedEvent event){

            if (userRepository.findByEmail("usuario@email.com") != null) {
                alreadySetup = true;
            }

        if (alreadySetup)

            return;
        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");

        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_STAFF", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User user = new User();
        user.setName("Usuário");
        user.setEmail("usuario@email.com");
        user.setPassword(passwordEncoder.encode("senha"));
        user.setRoles(Arrays.asList(adminRole));
        user.setEnabled(true);
        userRepository.save(user);

        alreadySetup = true;
     }

        @Transactional
        Privilege createPrivilegeIfNotFound (String name){

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

        @Transactional
        Role createRoleIfNotFound (
            String name, Collection< Privilege > privileges){

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }



    }
