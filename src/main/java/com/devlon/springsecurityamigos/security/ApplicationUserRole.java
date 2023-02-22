package com.devlon.springsecurityamigos.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.devlon.springsecurityamigos.security.ApplicationUserPermission.*;
import static com.devlon.springsecurityamigos.security.ApplicationUserPermission.COURSE_WRITE;

public enum ApplicationUserRole  {
    STUDENT(new HashSet<>()),
    ADMIN(new HashSet<>(Set.of(STUDENT_WRITE, STUDENT_READ, COURSE_WRITE, COURSE_READ))),
    ADMIN_TRAINEE(new HashSet<>(Set.of(STUDENT_READ, COURSE_READ)));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority>  getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission-> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }
}
