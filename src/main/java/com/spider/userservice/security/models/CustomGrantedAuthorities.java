package com.spider.userservice.security.models;

import com.spider.userservice.models.Role;
import org.springframework.security.core.GrantedAuthority;

public class CustomGrantedAuthorities implements GrantedAuthority {
    private String authority;
    public CustomGrantedAuthorities(Role role) {
        this.authority = role.getName();
    }
    @Override
    public String getAuthority() {
        return "";
    }
}
