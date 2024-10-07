package com.secured_template.infra.security.roles;

import com.secured_template.infra.security.SecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class RolesSecurityFilterChain {

    private  final SetupRoleHierarchy setupRoleHierarchy;
    private final SecurityFilter securityFilter;

    public RolesSecurityFilterChain(SetupRoleHierarchy setupRoleHierarchy, SecurityFilter securityFilter) {
        this.setupRoleHierarchy = setupRoleHierarchy;
        this.securityFilter = securityFilter;
    }
//The endpoint /roleHierarchy is protected with ROLE_STAFF in order to prove that the webSecurityExpressionHandler is working.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(authorize -> authorize
                        .expressionHandler(setupRoleHierarchy.customWebSecurityExpressionHandler())
                        .requestMatchers(HttpMethod.GET, "/users/acaoRestrita")
                        .hasRole("ADMIN")
                        .and()
                        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class))
                .build();

    }
}
