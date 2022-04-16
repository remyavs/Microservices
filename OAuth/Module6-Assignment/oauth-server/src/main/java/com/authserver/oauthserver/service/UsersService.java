package com.authserver.oauthserver.service;

import com.authserver.oauthserver.model.Users;
import com.authserver.oauthserver.repository.UsersDBQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    UsersDBQuery usersDBQuery;

    @Override
    public UsersHelper loadUserByUsername(final String username) throws UsernameNotFoundException {
        Users users = null;
        try {
            users = usersDBQuery.getUserDetails(username);
            UsersHelper userDetail = new UsersHelper(users);
            return userDetail;
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
    }

}