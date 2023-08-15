package com.moka.auth.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomAuthorityUtils {
    @Value("${jwtsecurity.adminpersnltype}")
    private String adminPersnlId;

    private final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_CLIENT_USER", "ROLE_SUPPLY_USER", "ROLE_FACTORY_USER");
    private final List<GrantedAuthority> CLIENT_USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_CLIENT_USER");
    private final List<GrantedAuthority> SUPPLY_USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_SUPPLY_USER");
    private final List<GrantedAuthority> FACTORY_USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_FACTORY_USER");

    private final List<String> ADMIN_ROLES_STRING = List.of("ADMIN", "CLIENT_USER", "SUPPLY_USER");
    private final List<String> CLIENT_USER_ROLES_STRING = List.of("CLIENT_USER");
    private final List<String> SUPPLY_USER_ROLES_STRING = List.of("SUPPLY_USER");
    private final List<String> FACTORY_USER_ROLES_STRING = List.of("FACTORY_USER");



    // 메모리 상의 Role을 기반으로 권한 정보 생성.
    public List<GrantedAuthority> createAuthorities(String persnlTypeCode) {
        if (persnlTypeCode.equals(adminPersnlId)) {
            return ADMIN_ROLES;
        }
        if (persnlTypeCode.equals("PTC001")){
            return CLIENT_USER_ROLES;
        }
        if (persnlTypeCode.equals("PTD001")){
            return SUPPLY_USER_ROLES;
        }
        else {
            return FACTORY_USER_ROLES;
        }
    }

    // DB에 저장된 Role을 기반으로 권한 정보 생성
    public List<GrantedAuthority> createAuthorities(List<String> roles) {
       List<GrantedAuthority> authorities = roles.stream()
               .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
               .collect(Collectors.toList());
       return authorities;
    }

    // DB 저장 용
    public List<String> createRoles(String persnlTypeCode) {
        if (persnlTypeCode.equals(adminPersnlId)) {
            return ADMIN_ROLES_STRING;
        }
        if (persnlTypeCode.equals("PTC001")){
            return CLIENT_USER_ROLES_STRING;
        }
        if (persnlTypeCode.equals("PTD001")){
            return SUPPLY_USER_ROLES_STRING;
        }
        else {
            return FACTORY_USER_ROLES_STRING;
        }
    }
}
