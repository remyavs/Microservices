package com.authserver.oauthserver.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

public class Users {
    private Integer userId;
    private String username;
    private String password;
    private Collection<GrantedAuthority> listOfgrantedAuthorities = new ArrayList<>();

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<GrantedAuthority> getListOfgrantedAuthorities() {
        return listOfgrantedAuthorities;
    }

    public void setListOfgrantedAuthorities(Collection<GrantedAuthority> listOfgrantedAuthorities) {
        this.listOfgrantedAuthorities = listOfgrantedAuthorities;
    }
}
