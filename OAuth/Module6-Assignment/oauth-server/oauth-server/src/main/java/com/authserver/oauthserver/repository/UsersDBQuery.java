package com.authserver.oauthserver.repository;

import com.authserver.oauthserver.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class UsersDBQuery {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Users getUserDetails(String username) {
        Collection<GrantedAuthority> listOfgrantedAuthorities = new ArrayList<>();
        String userSQLQuery = "SELECT * FROM USERS WHERE USERNAME=?";
        List<Users> list = jdbcTemplate.query(userSQLQuery, new String[] { username },
                (ResultSet rs, int rowNum) -> {

                    Users user = new Users();
                    user.setUsername(username);
                    user.setPassword(rs.getString("PASSWORD"));
                    return user;
                });
        if (list.size() > 0) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
            listOfgrantedAuthorities.add(grantedAuthority);
            list.get(0).setListOfgrantedAuthorities(listOfgrantedAuthorities);
            return list.get(0);
        }
        return null;
    }
}
