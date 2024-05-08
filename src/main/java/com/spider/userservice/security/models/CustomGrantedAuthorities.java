package com.spider.userservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.spider.userservice.models.Role;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor
@JsonDeserialize
public class CustomGrantedAuthorities implements GrantedAuthority {
    private String authority;
    public CustomGrantedAuthorities(Role role) {
        this.authority = role.getName();
    }
    @Override
    public String getAuthority() {
        return authority;
    }
}
