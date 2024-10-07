package com.secured_template.infra.security.roles;

import com.secured_template.infra.security.SecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

public class SetupRoleHierarchy {

    private final SecurityFilter securityFilter;

    public SetupRoleHierarchy(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    //create this hierarchy in Spring Security by simply exposing a bean of type RoleHierarchy
    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_ADMIN > ROLE_STAFF \n ROLE_STAFF > ROLE_USER";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    //To include this role hierarchy in Spring Web Expressions, we add the roleHierarchy instance to the WebSecurityExpressionHandler:
    @Bean
    public DefaultWebSecurityExpressionHandler customWebSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }

    //Finally, add  the expressionHandler into http.authorizeRequests() in the FilterChain

}
