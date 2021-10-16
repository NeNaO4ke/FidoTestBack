package com.fido.fidotestback.domain;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AuthenticatedUser implements UserDetails {
    private String username;
    private String password;
    private Collection authorities;

    public AuthenticatedUser(String username, Collection authorities, String password){
        this.username = username;
        this.authorities = authorities;
        this.password = password;
    }

    @Override
    public Collection getAuthorities() {
        return this.authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
