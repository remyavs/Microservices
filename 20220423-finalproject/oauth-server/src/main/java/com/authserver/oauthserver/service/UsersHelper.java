package com.authserver.oauthserver.service;

import com.authserver.oauthserver.model.Users;
import org.springframework.security.core.userdetails.User;

public class UsersHelper extends User{

    private static final long serialVersionUID = 1L;
    public UsersHelper(Users user) {
        super(
                user.getUsername(),
                user.getPassword(),
                user.getListOfgrantedAuthorities()
        );
    }
}